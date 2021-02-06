package com.hesoyam.pharmacy.pharmacy.events;

import com.hesoyam.pharmacy.pharmacy.model.MedicineSale;
import org.springframework.context.ApplicationEvent;

import java.util.ArrayList;
import java.util.List;

public class OnMedicineSaleEvent extends ApplicationEvent {
    private List<MedicineSale> medicineSales;

    public OnMedicineSaleEvent(List<MedicineSale> medicineSales){
        super(medicineSales);
        this.medicineSales = medicineSales;
    }

    public List<MedicineSale> getMedicineSales() {
        return medicineSales;
    }

    public void setMedicineSales(List<MedicineSale> medicineSales) {
        this.medicineSales = medicineSales;
    }
}
