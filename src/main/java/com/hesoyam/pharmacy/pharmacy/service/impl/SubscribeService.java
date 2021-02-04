package com.hesoyam.pharmacy.pharmacy.service.impl;

import com.hesoyam.pharmacy.pharmacy.exceptions.InvalidSubscriptionRequestException;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.pharmacy.service.IPharmacyService;
import com.hesoyam.pharmacy.pharmacy.service.ISubscribeService;
import com.hesoyam.pharmacy.user.exceptions.PatientNotFoundException;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class SubscribeService implements ISubscribeService {

    @Autowired
    private IPharmacyService pharmacyService;

    @Autowired
    private IPatientService patientService;

    @Override
    public void subscribe(Long pharmacyId, User user) {
        Pharmacy pharmacy = pharmacyService.findOne(pharmacyId);
        Patient patient = getPatient(user);

        if(pharmacy.isPatientSubscribed(patient)){
            throw new InvalidSubscriptionRequestException("User already subscribed.");
        }

        pharmacy.getSubscribedPatients().add(patient);
        pharmacyService.update(pharmacy);
    }

    @Override
    public void unsubscribe(Long pharmacyId, User user) {
        Pharmacy pharmacy = pharmacyService.findOne(pharmacyId);
        Patient patient = getPatient(user);
        if(!pharmacy.isPatientSubscribed(patient)){
            throw new InvalidSubscriptionRequestException("User is not subscribed.");
        }

        pharmacy.getSubscribedPatients().remove(patient);
        pharmacyService.update(pharmacy);
    }

    @Override
    public boolean isSubscribed(Long pharmacyId, User user) {
        return patientService.isPatientSubscribedToPharmacy(user.getId(), pharmacyId);
    }

    private Patient getPatient(User user){
        try {
            return patientService.getById(user.getId());
        } catch (PatientNotFoundException patientNotFoundException) {
            throw new EntityNotFoundException();
        }
    }
}
