package com.hesoyam.pharmacy.user.service;

public interface IPatientService {
    Boolean isPatientSubscribedToPharmacy(Long patientId, Long pharmacyId);
}
