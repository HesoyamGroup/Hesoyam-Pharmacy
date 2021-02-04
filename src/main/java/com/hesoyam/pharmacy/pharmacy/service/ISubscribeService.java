package com.hesoyam.pharmacy.pharmacy.service;

import com.hesoyam.pharmacy.user.model.User;
import org.springframework.stereotype.Service;

@Service
public interface ISubscribeService {
    void subscribe(Long pharmacyId, User user);
    void unsubscribe(Long pharmacyId, User user);
    boolean isSubscribed(Long pharmacyId, User user);
}
