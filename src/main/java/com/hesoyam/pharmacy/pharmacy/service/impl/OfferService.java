package com.hesoyam.pharmacy.pharmacy.service.impl;

import com.hesoyam.pharmacy.pharmacy.repository.OfferRepository;
import com.hesoyam.pharmacy.pharmacy.service.IOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService implements IOfferService {

    @Autowired
    private OfferRepository offerRepository;
}
