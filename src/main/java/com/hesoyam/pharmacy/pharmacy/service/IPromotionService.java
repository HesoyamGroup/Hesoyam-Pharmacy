package com.hesoyam.pharmacy.pharmacy.service;

import com.hesoyam.pharmacy.pharmacy.dto.PromotionDTO;
import com.hesoyam.pharmacy.pharmacy.model.Promotion;
import com.hesoyam.pharmacy.user.model.User;

import java.util.List;

public interface IPromotionService {
    List<Promotion> getAllByAdministratorPharmacy(User user);

    Promotion create(User user, PromotionDTO candidatePromotion);
}
