package com.hesoyam.pharmacy.storage.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hesoyam.pharmacy.medicine.model.Medicine;

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

    @Column(name="stock")
    @Min(0)
    private int stock;



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

    public void setStock(int stock) {
        this.stock = stock;
    }
}
