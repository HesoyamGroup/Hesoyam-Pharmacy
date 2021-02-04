package com.hesoyam.pharmacy.storage.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hesoyam.pharmacy.medicine.model.Medicine;
import com.hesoyam.pharmacy.pharmacy.model.OrderItem;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Entity
@Table(uniqueConstraints= @UniqueConstraint(columnNames={"storage_id", "medicine_id"}))
public class StorageItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="storage_id", nullable = false)
    @JsonBackReference
    private Storage storage;

    @ManyToOne(fetch=FetchType.EAGER, optional=false)
    @JoinColumn(name="medicine_id", nullable = false)
    private Medicine medicine;

    @Column(name="stock", nullable = false)
    @Min(0)
    private int stock;

    @Column(name="reserved", nullable = false)
    @Min(0)
    private int reserved;

    public StorageItem(){

    }
    public StorageItem(Storage storage, Medicine medicine) {
        this.storage = storage;
        this.medicine = medicine;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) throws IllegalArgumentException {
        if(stock < this.reserved){
            throw new IllegalArgumentException("Stock must be higher or equal than reserved.");
        }
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getReserved() {
        return reserved;
    }

    public void setReserved(int reserved) throws IllegalArgumentException {
        if(reserved > this.stock){
            throw new IllegalArgumentException("Reserved value must be less or equal than stock value.");
        }
        this.reserved = reserved;
    }

    public int getAvailable(){
        return stock - reserved;
    }

    public boolean canFulfillOrderItem(OrderItem orderItem){
        return this.getAvailable() >= orderItem.getQuantity();
    }

    public void incReservedBy(int value){
        setReserved(reserved + value);
    }
    public void decReservedBy(int value) {
        setReserved(reserved - value);
    }
}
