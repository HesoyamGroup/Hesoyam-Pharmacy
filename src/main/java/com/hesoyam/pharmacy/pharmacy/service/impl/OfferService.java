package com.hesoyam.pharmacy.pharmacy.service.impl;

import com.hesoyam.pharmacy.pharmacy.dto.CreateOfferDTO;
import com.hesoyam.pharmacy.pharmacy.dto.OfferDTO;
import com.hesoyam.pharmacy.pharmacy.dto.OfferFilterCriteria;
import com.hesoyam.pharmacy.pharmacy.exceptions.InvalidCreateOfferException;
import com.hesoyam.pharmacy.pharmacy.exceptions.InvalidEditOfferException;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService implements IOfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IStorageService storageService;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
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
        if(!storage.performResourceReservation(order)){
            throw new InvalidCreateOfferException("Storage can't fulfill the order.");
        }
        try {
            offer = offerRepository.save(offer);
            order.getOffers().add(offer);
            orderService.update(order);
            storageService.update(storage);
            return OfferMapper.mapOfferToOfferDTO(offer);
        }catch (DataIntegrityViolationException e){
            throw new InvalidCreateOfferException("Offer is not valid.");
        }
    }

    @Override
    public List<OfferDTO> getUserOffers(OfferFilterCriteria offerFilterCriteria, User user) {
        List<Offer> offers = offerRepository.getSupplierOffersFiltered(offerFilterCriteria, user.getId(), PageRequest.of(offerFilterCriteria.getPage()-1, 8));
        return offers.stream().map((offer) -> OfferMapper.mapOfferToOfferDTO(offer)).collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)//In case it's getting rejected in the meantime.
    public OfferDTO cancel(Long id, User user) {
        Offer offer = offerRepository.getOfferByIdAndSupplier_Id(id, user.getId());
        if(offer == null) throw new EntityNotFoundException();
        Order order = offer.getOrder();
        if(!order.isOfferEditable(offer)){
            //TODO: Check if offer can be cancelled. (after order part is done)
            //TODO: For now, we will check if it's before deadline and status is created.
            throw new InvalidEditOfferException("Offer is not editable at the moment.");
        }
        offer.setOfferStatus(OfferStatus.CANCELLED);
        Storage storage = storageService.getUserStorage(user);
        storage.releaseResourceReservation(order);
        storageService.update(storage);

        return OfferMapper.mapOfferToOfferDTO(offerRepository.save(offer));
    }
}
