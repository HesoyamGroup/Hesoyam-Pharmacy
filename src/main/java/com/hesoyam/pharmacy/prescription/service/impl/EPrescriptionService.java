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
import com.hesoyam.pharmacy.pharmacy.model.InventoryItem;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.pharmacy.service.IInventoryItemService;
import com.hesoyam.pharmacy.pharmacy.service.IPharmacyService;
import com.hesoyam.pharmacy.prescription.dto.EPrescriptionQRData;
import com.hesoyam.pharmacy.prescription.exception.InvalidEPrescriptionFormat;
import com.hesoyam.pharmacy.prescription.model.EPrescription;
import com.hesoyam.pharmacy.prescription.model.PrescriptionItem;
import com.hesoyam.pharmacy.prescription.model.PrescriptionStatus;
import com.hesoyam.pharmacy.prescription.repository.EPrescriptionRepository;
import com.hesoyam.pharmacy.prescription.service.IEPrescriptionService;
import com.hesoyam.pharmacy.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EPrescriptionService implements IEPrescriptionService {

    @Autowired
    private EPrescriptionRepository ePrescriptionRepository;

    @Autowired
    private IPharmacyService pharmacyService;

    @Autowired
    private IInventoryItemService inventoryItemService;

    @Override
    public List<PharmacyWithPrescriptionPriceDTO> get(File qrCodeImage, User user) {
        if(!isImage(qrCodeImage)) throw new InvalidEPrescriptionFormat("Uploaded file is not an image.");

        try {
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(qrCodeImage))));
            Result result = new MultiFormatReader().decode(binaryBitmap);
            EPrescriptionQRData ePrescriptionQRData = extractEPrescriptionDataFromDecodedQRCode(result);
            EPrescription ePrescription = ePrescriptionRepository.getEPrescriptionByIdAndPatient_IdAndPrescriptionStatus(ePrescriptionQRData.getId(), user.getId(), PrescriptionStatus.ACTIVE);
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

    private PharmacyWithPrescriptionPriceDTO mapPharmacyToPharmacyWithPrescriptionDataDTO(Pharmacy pharmacy, int price){
        return new PharmacyWithPrescriptionPriceDTO(pharmacy.getId(), pharmacy.getName(), pharmacy.getAddress(), price);
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
                pharmacyInfo.add(mapPharmacyToPharmacyWithPrescriptionDataDTO(pharmacy, finalPrice));
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
