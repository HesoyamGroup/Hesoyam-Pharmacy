package com.hesoyam.pharmacy.prescription.service;

import com.hesoyam.pharmacy.pharmacy.dto.PharmacyWithPrescriptionPriceDTO;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.prescription.dto.CompletePrescriptionDTO;
import com.hesoyam.pharmacy.prescription.model.EPrescription;
import com.hesoyam.pharmacy.user.model.User;

import java.io.File;
import java.util.List;

public interface IEPrescriptionService {
    List<PharmacyWithPrescriptionPriceDTO> get(File qrCodeImage, User user);
    EPrescription complete(CompletePrescriptionDTO completePrescriptionDTO, User user);
}
