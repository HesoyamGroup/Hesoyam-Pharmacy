package com.hesoyam.pharmacy.prescription.service;

import com.hesoyam.pharmacy.prescription.dto.EPrescriptionUploadResponse;
import com.hesoyam.pharmacy.prescription.dto.PharmacyWithPrescriptionPriceDTO;
import com.hesoyam.pharmacy.prescription.dto.CompletePrescriptionDTO;
import com.hesoyam.pharmacy.prescription.model.EPrescription;
import com.hesoyam.pharmacy.user.model.Patient;

import java.io.File;
import java.util.List;

public interface IEPrescriptionService {
    EPrescriptionUploadResponse get(File qrCodeImage, Patient patient);
    EPrescription complete(CompletePrescriptionDTO completePrescriptionDTO, Patient patient);
    int countEPrescriptionsByPatient(Patient patient);
}
