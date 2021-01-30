package com.hesoyam.pharmacy.medicine.service.impl;

import com.hesoyam.pharmacy.medicine.model.CompositionItem;
import com.hesoyam.pharmacy.medicine.repository.CompositionItemRepository;
import com.hesoyam.pharmacy.medicine.service.ICompositionItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompositionItemService implements ICompositionItemService {
    @Autowired
    private CompositionItemRepository compositionItemRepository;

    @Override
    public List<CompositionItem> getAll() {
        return compositionItemRepository.findAll();
    }
}
