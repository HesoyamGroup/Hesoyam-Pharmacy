package com.hesoyam.pharmacy.pharmacy.service.impl;

import com.hesoyam.pharmacy.pharmacy.DTO.CreateOfferDTO;
import com.hesoyam.pharmacy.pharmacy.DTO.OfferDTO;
import com.hesoyam.pharmacy.pharmacy.exceptions.InvalidCreateOfferException;
import com.hesoyam.pharmacy.pharmacy.mapper.OfferMapper;
import com.hesoyam.pharmacy.pharmacy.model.Offer;
import com.hesoyam.pharmacy.pharmacy.model.OfferStatus;
import com.hesoyam.pharmacy.pharmacy.model.Order;
import com.hesoyam.pharmacy.pharmacy.repository.OfferRepository;
import com.hesoyam.pharmacy.pharmacy.service.IOfferService;
import com.hesoyam.pharmacy.pharmacy.service.IOrderService;
import com.hesoyam.pharmacy.storage.model.Storage;
import com.hesoyam.pharmacy.storage.service.IStorageService;
import com.hesoyam.pharmacy.user.model.Supplier;
import com.hesoyam.pharmacy.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService implements IOfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IStorageService storageService;


    @Override
    public OfferDTO create(CreateOfferDTO createOfferDTO, User user) {
        Order order = orderService.get(createOfferDTO.getOrderId());
        Storage storage = storageService.getUserStorage(user);
        Offer offer = OfferMapper.mapCreateOfferDTOToOffer(createOfferDTO);

        offer.setOfferStatus(OfferStatus.CREATED);
        offer.setSupplier(new Supplier(user.getId()));
        offer.setOrder(order);
        if(!order.isOfferValidForOrder(offer)) {
            throw new InvalidCreateOfferException("Offer is not valid!");
        }
        try {
            offer = offerRepository.save(offer);
            return OfferMapper.mapOfferToOfferDTO(offer);
        }catch (DataIntegrityViolationException e){
            throw new InvalidCreateOfferException("Offer is not valid.");
        }
    }
}
