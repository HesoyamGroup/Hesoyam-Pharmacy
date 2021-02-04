package com.hesoyam.pharmacy.pharmacy.mapper;

import com.hesoyam.pharmacy.pharmacy.dto.CreateOfferDTO;
import com.hesoyam.pharmacy.pharmacy.dto.OfferDTO;
import com.hesoyam.pharmacy.pharmacy.model.Offer;

public class OfferMapper {

    private OfferMapper() {}

    public static Offer mapCreateOfferDTOToOffer(CreateOfferDTO createOfferDTO){
        return new Offer(createOfferDTO.getTotalPrice(), createOfferDTO.getDeliveryDate());
    }

    public static OfferDTO mapOfferToOfferDTO(Offer offer){
        return new OfferDTO(offer.getId(),offer.getTotalPrice(), offer.getDeliveryDate(), offer.getOfferStatus(), offer.getOrder().getId());
    }
}
