package com.hesoyam.pharmacy.medicine.events;

import com.hesoyam.pharmacy.user.model.Administrator;
import org.springframework.context.ApplicationEvent;

import java.util.List;

public class MedicineRunningLowEvent extends ApplicationEvent {
    private String medicineName;
    private List<Administrator> administrators;

    public MedicineRunningLowEvent(String medicineName, List<Administrator> administrators) {
        super(medicineName);
        this.administrators = administrators;
        this.medicineName = medicineName;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public List<Administrator> getAdministrators() {
        return administrators;
    }

    public void setAdministrators(List<Administrator> administrators) {
        this.administrators = administrators;
    }
}
