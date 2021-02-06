package com.hesoyam.pharmacy.prescription.service;

import com.hesoyam.pharmacy.pharmacy.dto.PharmacyWithPrescriptionPriceDTO;
import com.hesoyam.pharmacy.prescription.dto.CompletePrescriptionDTO;
import com.hesoyam.pharmacy.prescription.model.EPrescription;
import com.hesoyam.pharmacy.user.model.Patient;

import java.io.File;
import java.util.List;

public interface IEPrescriptionService {
    List<PharmacyWithPrescriptionPriceDTO> get(File qrCodeImage, Patient patient);
    EPrescription complete(CompletePrescriptionDTO completePrescriptionDTO, Patient patient);
}