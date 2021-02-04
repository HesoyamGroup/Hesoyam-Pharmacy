package com.hesoyam.pharmacy.pharmacy.service;

import com.hesoyam.pharmacy.pharmacy.DTO.CreateOfferDTO;
import com.hesoyam.pharmacy.pharmacy.DTO.OfferDTO;
import com.hesoyam.pharmacy.pharmacy.DTO.OfferFilterCriteria;
import com.hesoyam.pharmacy.user.model.User;

import java.util.List;

public interface IOfferService {
    OfferDTO create(CreateOfferDTO createOfferDTO, User user);
    List<OfferDTO> getUserOffers(OfferFilterCriteria offerFilterCriteria, User user);
    OfferDTO cancel(Long id, User user);
}
