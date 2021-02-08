package com.hesoyam.pharmacy.unit.storage;

import com.hesoyam.pharmacy.pharmacy.model.OrderItem;
import com.hesoyam.pharmacy.storage.model.StorageItem;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StorageItemTests {

    @Test
    void setInvalidReservedValueTest(){

        StorageItem storageItem = new StorageItem();
        storageItem.setStock(20);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            storageItem.setReserved(21);
        });
    }

    @Test
    void setValidReservedValueTest(){
        StorageItem storageItem1 = new StorageItem();
        storageItem1.setStock(10);

        StorageItem storageItem2 = new StorageItem();
        storageItem2.setStock(15);

        //Assert values are set and no exceptions were found.
        Assertions.assertEquals(10, storageItem1.getStock());
        Assertions.assertEquals(15, storageItem2.getStock());
    }

    @Test
    void canFulfillOrderItem(){
        StorageItem storageItem = new StorageItem();
        storageItem.setStock(10);
        OrderItem orderItem = getOrderItem(9);
        Assert.assertTrue(storageItem.canFulfillOrderItem(orderItem));
        OrderItem orderItem1 = getOrderItem(10);
        Assert.assertTrue(storageItem.canFulfillOrderItem(orderItem1));
    }

    @Test
    void cannotFulfilOrderItem(){
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
