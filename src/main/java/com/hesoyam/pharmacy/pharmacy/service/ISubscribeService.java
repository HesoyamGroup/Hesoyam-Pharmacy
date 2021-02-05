package com.hesoyam.pharmacy.pharmacy.service;

import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ISubscribeService {
    void subscribe(Long pharmacyId, User user);
    void unsubscribe(Long pharmacyId, User user);
    boolean isSubscribed(Long pharmacyId, User user);
    List<Patient> getSubscribedPatientsByPharmacy(Long pharmacyId);
}
