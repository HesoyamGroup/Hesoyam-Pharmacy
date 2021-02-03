package com.hesoyam.pharmacy.pharmacy.service;

import com.hesoyam.pharmacy.pharmacy.DTO.CreateOfferDTO;
import com.hesoyam.pharmacy.pharmacy.DTO.OfferDTO;
import com.hesoyam.pharmacy.user.model.User;

public interface IOfferService {
    OfferDTO create(CreateOfferDTO createOfferDTO, User user);
}
