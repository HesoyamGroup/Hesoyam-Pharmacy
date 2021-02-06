package com.hesoyam.pharmacy.prescription.service;

import com.hesoyam.pharmacy.prescription.exceptions.PatientIsAllergicException;
import com.hesoyam.pharmacy.prescription.model.EPrescription;
import com.hesoyam.pharmacy.prescription.model.PrescriptionItem;
import com.hesoyam.pharmacy.user.model.Patient;

import java.util.List;

public interface IPrescriptionService {
    EPrescription createPrescription(List<PrescriptionItem> prescriptionItems, Patient patient) throws PatientIsAllergicException;
}
