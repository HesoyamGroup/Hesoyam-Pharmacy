package com.hesoyam.pharmacy.pharmacy.dto;

import com.hesoyam.pharmacy.pharmacy.model.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ShowOrdersDTO {
    private Long id;
    private LocalDateTime deadLine;
    private String pharmacyName;
    private List<ShowOrderItemDTO> showOrderItemDTOS;

    public ShowOrdersDTO(){
        //Empty ctor for JSON serializer
    }

    public ShowOrdersDTO(Order order){
        this.id = order.getId();
        this.deadLine = order.getDeadLine();
        this.pharmacyName = order.getPharmacy().getName();
        this.showOrderItemDTOS = new ArrayList<>();
        order.getOrderItems().forEach(item -> showOrderItemDTOS.add(new ShowOrderItemDTO(item)));
    }

    public ShowOrdersDTO(Long id, LocalDateTime deadLine, String pharmacyName, List<ShowOrderItemDTO> showOrderItemDTOS) {
        this.id = id;
        this.deadLine = deadLine;
        this.pharmacyName = pharmacyName;
        this.showOrderItemDTOS = showOrderItemDTOS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDateTime deadLine) {
        this.deadLine = deadLine;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public List<ShowOrderItemDTO> getShowOrderItemDTOS() {
        if(showOrderItemDTOS == null)
            return new ArrayList<>();
        return showOrderItemDTOS;
    }

    public void setShowOrderItemDTOS(List<ShowOrderItemDTO> showOrderItemDTOS) {
        this.showOrderItemDTOS = showOrderItemDTOS;
    }
}
