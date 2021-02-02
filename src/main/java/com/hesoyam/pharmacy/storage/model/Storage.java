package com.hesoyam.pharmacy.storage.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy="storage")
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
}
