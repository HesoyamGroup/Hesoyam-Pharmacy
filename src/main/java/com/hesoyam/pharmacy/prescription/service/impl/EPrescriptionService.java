package com.hesoyam.pharmacy.prescription.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.hesoyam.pharmacy.medicine.model.Medicine;
import com.hesoyam.pharmacy.pharmacy.dto.PharmacyWithPrescriptionPriceDTO;
import com.hesoyam.pharmacy.pharmacy.events.OnMedicineSaleEvent;
import com.hesoyam.pharmacy.pharmacy.model.InventoryItem;
import com.hesoyam.pharmacy.pharmacy.model.MedicineSale;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.pharmacy.service.IInventoryItemService;
import com.hesoyam.pharmacy.pharmacy.service.IPharmacyService;
import com.hesoyam.pharmacy.prescription.dto.CompletePrescriptionDTO;
import com.hesoyam.pharmacy.prescription.dto.EPrescriptionQRData;
import com.hesoyam.pharmacy.prescription.events.OnEPrescriptionCompletedEvent;
import com.hesoyam.pharmacy.prescription.exception.InvalidCompleteEPrescriptionException;
import com.hesoyam.pharmacy.prescription.exception.InvalidEPrescriptionFormat;
import com.hesoyam.pharmacy.prescription.model.EPrescription;
import com.hesoyam.pharmacy.prescription.model.PrescriptionItem;
import com.hesoyam.pharmacy.prescription.model.PrescriptionStatus;
import com.hesoyam.pharmacy.prescription.repository.EPrescriptionRepository;
import com.hesoyam.pharmacy.prescription.service.IEPrescriptionService;
import com.hesoyam.pharmacy.user.model.LoyaltyAccount;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.service.ILoyaltyAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EPrescriptionService implements IEPrescriptionService {

    @Autowired
    private EPrescriptionRepository ePrescriptionRepository;

    @Autowired
    private IPharmacyService pharmacyService;

    @Autowired
    private IInventoryItemService inventoryItemService;

    @Autowired
    private ILoyaltyAccountService loyaltyAccountService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public List<PharmacyWithPrescriptionPriceDTO> get(File qrCodeImage, Patient patient) {
        if(!isImage(qrCodeImage)) throw new InvalidEPrescriptionFormat("Uploaded file is not an image.");

        try {
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(qrCodeImage))));
            Result result = new MultiFormatReader().decode(binaryBitmap);
            EPrescriptionQRData ePrescriptionQRData = extractEPrescriptionDataFromDecodedQRCode(result);
            EPrescription ePrescription = ePrescriptionRepository.getEPrescriptionByIdAndPatient_IdAndPrescriptionStatus(ePrescriptionQRData.getId(), patient.getId(), PrescriptionStatus.ACTIVE);
            if(ePrescription == null) throw new EntityNotFoundException();

            return getPharmaciesWhoCanFulfillPrescription(ePrescription);
        }catch (IOException e){
            e.printStackTrace();
            throw new InvalidEPrescriptionFormat("EPrescription could not be read from provided QR code.");
        }catch (NotFoundException e){
            e.printStackTrace();
            throw new InvalidEPrescriptionFormat("QR could not be found on your image.");
        }
    }

    @Override
    //TODO: Transactional. (!!!!!!)
    public EPrescription complete(CompletePrescriptionDTO completePrescriptionDTO, Patient patient) {
        EPrescription ePrescription = ePrescriptionRepository.getEPrescriptionByIdAndPatient_IdAndPrescriptionStatus(completePrescriptionDTO.getPrescriptionId(), patient.getId(), PrescriptionStatus.ACTIVE);
        if(ePrescription == null) throw new EntityNotFoundException();
        Pharmacy pharmacy = pharmacyService.findOne(completePrescriptionDTO.getPharmacyId());
        boolean canFulfil = true;
        double price = 0;
        int loyaltyPoints = 0;

        LoyaltyAccount loyaltyAccount = loyaltyAccountService.getPatientLoyaltyAccount(patient);

        List<MedicineSale> medicineSales = new ArrayList<>();
        for(PrescriptionItem prescriptionItem : ePrescription.getPrescriptionItems()){
            Medicine medicine = prescriptionItem.getMedicine();
            if(!pharmacyService.canPharmacyOfferMedicineQuantity(pharmacy.getId(), medicine.getId(), prescriptionItem.getQuantity())){
                throw new InvalidCompleteEPrescriptionException("Not enough medicine!");
            }
            InventoryItem inventoryItem = inventoryItemService.getInventoryItemByPharmacyIdAndMedicineId(pharmacy.getId(), medicine.getId());
            inventoryItem.setAvailable(inventoryItem.getAvailable() - prescriptionItem.getQuantity());
            inventoryItemService.update(inventoryItem);
            loyaltyPoints += medicine.getLoyaltyPoints();
            double calculatedPrice = inventoryItem.calculateTodayPriceForQuantity(prescriptionItem.getQuantity());
            calculatedPrice = loyaltyAccount.getDiscountedPrice(calculatedPrice);
            price += calculatedPrice;
            medicineSales.add(new MedicineSale(LocalDateTime.now(), calculatedPrice, pharmacy, medicine));
        }
        ePrescription.setPrescriptionStatus(PrescriptionStatus.COMPLETED);
        ePrescriptionRepository.save(ePrescription);

        loyaltyAccount.addPoints(loyaltyPoints);
        loyaltyAccountService.update(loyaltyAccount);

        eventPublisher.publishEvent(new OnMedicineSaleEvent(medicineSales));
        eventPublisher.publishEvent(new OnEPrescriptionCompletedEvent(ePrescription));

        return ePrescription;

    }

    private PharmacyWithPrescriptionPriceDTO mapPharmacyToPharmacyWithPrescriptionDataDTO(Pharmacy pharmacy, double price, Long eprescriptionId, double discountedPrice){
        return new PharmacyWithPrescriptionPriceDTO(pharmacy.getId(), pharmacy.getName(), pharmacy.getAddress(), price, pharmacy.getRating(), eprescriptionId, discountedPrice);
    }

    private List<PharmacyWithPrescriptionPriceDTO> getPharmaciesWhoCanFulfillPrescription(EPrescription ePrescription){
        List<Pharmacy> hasMedicineList = pharmacyService.getPharmaciesByMedicineAvailability(ePrescription.getMedicineIds());
        List<PharmacyWithPrescriptionPriceDTO> pharmacyInfo = new ArrayList<>();
        for(Pharmacy pharmacy : hasMedicineList) {
            int finalPrice = 0;
            boolean canFulfill = true;
            for (PrescriptionItem prescriptionItem : ePrescription.getPrescriptionItems()) {
                Medicine medicine = prescriptionItem.getMedicine();
                if (!pharmacyService.canPharmacyOfferMedicineQuantity(pharmacy.getId(), medicine.getId(), prescriptionItem.getQuantity())) {
                    canFulfill = false;
                    break;
                }
                InventoryItem inventoryItem = inventoryItemService.getInventoryItemByPharmacyIdAndMedicineId(pharmacy.getId(), medicine.getId());
                finalPrice += inventoryItem.calculateTodayPriceForQuantity(prescriptionItem.getQuantity());
            }
            if (canFulfill) {
                pharmacyInfo.add(mapPharmacyToPharmacyWithPrescriptionDataDTO(pharmacy, finalPrice, ePrescription.getId(), loyaltyAccountService.calculateDiscountedPrice(ePrescription.getPatient(), finalPrice)));
            }
        }
        return pharmacyInfo;
    }

    private EPrescriptionQRData extractEPrescriptionDataFromDecodedQRCode(Result result) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.readValue(result.toString(), EPrescriptionQRData.class);
    }

    private boolean isImage(File file){
        if(file == null) return false;
        String mimetype= new MimetypesFileTypeMap().getContentType(file);
        String type = mimetype.split("/")[0];
        if(!type.equals("image")) return false;

        return true;
    }
}
