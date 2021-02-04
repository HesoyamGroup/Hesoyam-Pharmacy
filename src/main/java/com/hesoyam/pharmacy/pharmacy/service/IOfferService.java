package com.hesoyam.pharmacy.pharmacy.service;

import com.hesoyam.pharmacy.pharmacy.dto.CreateOfferDTO;
import com.hesoyam.pharmacy.pharmacy.dto.OfferDTO;
import com.hesoyam.pharmacy.pharmacy.dto.OfferFilterCriteria;
import com.hesoyam.pharmacy.user.model.User;

import java.util.List;

public interface IOfferService {
    OfferDTO create(CreateOfferDTO createOfferDTO, User user);
    List<OfferDTO> getUserOffers(OfferFilterCriteria offerFilterCriteria, User user);
    OfferDTO cancel(Long id, User user);
}
