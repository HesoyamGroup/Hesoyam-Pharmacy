package com.hesoyam.pharmacy.unit.storage;

import com.hesoyam.pharmacy.pharmacy.model.OrderItem;
import com.hesoyam.pharmacy.storage.model.StorageItem;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class StorageItemTests {

    @Test
    public void setInvalidReservedValueTest(){

        StorageItem storageItem = new StorageItem();
        storageItem.setStock(20);

        Assert.assertThrows(IllegalArgumentException.class, () -> {
            storageItem.setReserved(21);
        });
    }

    @Test
    public void setValidReservedValueTest(){
        StorageItem storageItem1 = new StorageItem();
        storageItem1.setStock(10);

        StorageItem storageItem2 = new StorageItem();
        storageItem2.setStock(15);
    }

    @Test
    public void canFulfillOrderItem(){
        StorageItem storageItem = new StorageItem();
        storageItem.setStock(10);
        OrderItem orderItem = getOrderItem(9);
        Assert.assertTrue(storageItem.canFulfillOrderItem(orderItem));
        OrderItem orderItem1 = getOrderItem(10);
        Assert.assertTrue(storageItem.canFulfillOrderItem(orderItem1));
    }

    @Test
    public void cannotFulfilOrderItem(){
        StorageItem storageItem = new StorageItem();
        storageItem.setStock(10);
        OrderItem orderItem = getOrderItem(11);
        Assert.assertFalse(storageItem.canFulfillOrderItem(orderItem));
    }

    private OrderItem getOrderItem(int quantity){
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(quantity);
        return orderItem;
    }
}
