package com.hesoyam.pharmacy.pharmacy.service.impl;

import com.hesoyam.pharmacy.pharmacy.dto.PromotionDTO;
import com.hesoyam.pharmacy.pharmacy.events.OnNewPromotionEvent;
import com.hesoyam.pharmacy.pharmacy.model.Promotion;
import com.hesoyam.pharmacy.pharmacy.repository.PromotionRepository;
import com.hesoyam.pharmacy.pharmacy.service.IPromotionService;
import com.hesoyam.pharmacy.user.model.Administrator;
import com.hesoyam.pharmacy.user.model.Patient;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PromotionService implements IPromotionService {
    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private SubscribeService subscribeService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public List<Promotion> getAllByAdministratorPharmacy(User user){
        if(user == null) throw new EntityNotFoundException();

        Administrator administrator = administratorRepository.getOne(user.getId());

        return promotionRepository.findAllByPharmacy_Id(administrator.getPharmacy().getId());
    }

    @Override
    public Promotion create(User user, PromotionDTO candidatePromotion) {
        if(user == null) throw new EntityNotFoundException();
        Administrator administrator = administratorRepository.getOne(user.getId());

        Promotion newPromotion = new Promotion();
        newPromotion.setAdministrator(administrator);
        newPromotion.setDateTimeRange(candidatePromotion.getRange());
        newPromotion.setTitle(candidatePromotion.getTitle());
        newPromotion.setDescription(candidatePromotion.getDescription());
        newPromotion.setPharmacy(administrator.getPharmacy());

        Promotion promotion =  promotionRepository.save(newPromotion);

        //Send mail to patients
        applicationEventPublisher.publishEvent(new OnNewPromotionEvent(promotion));
        return promotion;
    }
}
