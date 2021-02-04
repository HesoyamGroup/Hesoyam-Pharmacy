package com.hesoyam.pharmacy.storage.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hesoyam.pharmacy.pharmacy.model.Order;
import com.hesoyam.pharmacy.pharmacy.model.OrderItem;
import com.hesoyam.pharmacy.user.model.Supplier;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Storage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="storage", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<StorageItem> storageItems;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="supplier_id", nullable = false)
    @JsonBackReference
    private Supplier supplier;

    public Storage() {
        storageItems = new ArrayList<>();
    }
    public Storage(Supplier supplier){
        this();
        this.supplier = supplier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<StorageItem> getStorageItems() {
        return storageItems;
    }

    public void setStorageItems(List<StorageItem> storageItems) {
        this.storageItems = storageItems;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }


    public boolean performResourceReservation(Order order){
        if(!canFulfillOrder(order)) return false;

        for(OrderItem orderItem : order.getOrderItems()){
            for(StorageItem storageItem : storageItems){
                if(storageItem.getMedicine().getId().equals(orderItem.getMedicine().getId())){
                    storageItem.incReservedBy(orderItem.getQuantity());
                }
            }
        }

        return true;
    }

    public void releaseResourceReservation(Order order){
        for(OrderItem orderItem : order.getOrderItems()){
            for(StorageItem storageItem : storageItems){
                if(storageItem.getMedicine().getId().equals(orderItem.getMedicine().getId())){
                    storageItem.decReservedBy(orderItem.getQuantity());
                }
            }
        }
    }

    private boolean canFulfillOrder(Order order){
        List<OrderItem> orderItems = order.getOrderItems();
        if(!isAllItemsAvailable(orderItems)) return false;
        return  canFulfillOrderRequirments(orderItems);
    }

    private boolean canFulfillOrderRequirments(List<OrderItem> orderItems){
        for(OrderItem orderItem : orderItems){
            for(StorageItem storageItem : storageItems){
                if(orderItem.getMedicine().getId().equals(storageItem.getMedicine().getId())){
                    if(!storageItem.canFulfillOrderItem(orderItem)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean isAllItemsAvailable(List<OrderItem> orderItems){
        for(OrderItem orderItem : orderItems){
            boolean found = false;
            for(StorageItem storageItem : storageItems){
                if(storageItem.getMedicine().getId().equals(orderItem.getMedicine().getId())){
                    found = true;
                    break;
                }
            }
            if(!found) return false;
        }
        return true;
    }
}
