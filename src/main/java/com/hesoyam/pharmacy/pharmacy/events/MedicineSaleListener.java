package com.hesoyam.pharmacy.pharmacy.events;

import com.hesoyam.pharmacy.pharmacy.model.MedicineSale;
import com.hesoyam.pharmacy.pharmacy.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedicineSaleListener implements ApplicationListener<OnMedicineSaleEvent> {
    @Autowired
    private SaleRepository saleRepository;

    @Override
    public void onApplicationEvent(OnMedicineSaleEvent onMedicineSaleEvent) {
        this.recordMedicineSale(onMedicineSaleEvent.getMedicineSales());
    }

    private void recordMedicineSale(List<MedicineSale> medicineSales){
        saleRepository.saveAll(medicineSales);
    }
}
