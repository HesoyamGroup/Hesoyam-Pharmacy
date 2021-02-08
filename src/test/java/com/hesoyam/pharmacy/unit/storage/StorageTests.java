package com.hesoyam.pharmacy.unit.storage;

import com.hesoyam.pharmacy.medicine.model.Medicine;
import com.hesoyam.pharmacy.pharmacy.model.Order;
import com.hesoyam.pharmacy.pharmacy.model.OrderItem;
import com.hesoyam.pharmacy.storage.model.Storage;
import com.hesoyam.pharmacy.storage.model.StorageItem;
import org.aspectj.weaver.ast.Or;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

class StorageTests {
    @Test
    void performResourceReservationTest(){
        Storage storage = getStorage();
        Order canBeFulfilledOrder = getOrderWhoCanFulfill();
        Order cannotBeFulfilledOrder = getOrderWhoCannotFulfill();
        //Asserting if we can reserve the medicine or not.
        Assert.assertTrue(storage.performResourceReservation(canBeFulfilledOrder));
        Assert.assertFalse(storage.performResourceReservation(cannotBeFulfilledOrder));
    }

    @Test
    void storageStatusUpdatedAfterReservationTest(){
        Storage storage = getStorage();
        Order canBeFulfilledOrder = getOrderWhoCanFulfill();
        OrderItem firstOrderItem = canBeFulfilledOrder.getOrderItems().get(0);
        StorageItem firstStorageItem = storage.getStorageItems().stream().filter(item -> item.getMedicine().getId().equals(firstOrderItem.getMedicine().getId())).findFirst().orElse(null);
        int oldReservedValue = firstStorageItem.getReserved();
        int supposedNewValue = oldReservedValue + firstOrderItem.getQuantity();

        storage.performResourceReservation(canBeFulfilledOrder);

        Assertions.assertEquals(firstStorageItem.getReserved(), supposedNewValue);
    }

    @Test
    void releaseReservationStatus(){
        Storage storage = getStorage();
        Order order = getReleasingOrder();
        OrderItem orderItem = order.getOrderItems().get(0);
        StorageItem firstStorageItem = storage.getStorageItems().stream().filter(item -> item.getMedicine().getId().equals(orderItem.getMedicine().getId())).findFirst().orElse(null);
        int oldReservedValue = firstStorageItem.getReserved();
        int newReservedValue = oldReservedValue - orderItem.getQuantity();
        storage.releaseResourceReservation(order);
        //Assert that the reserved value was updated properly after using cancelling an offer.
        Assertions.assertEquals(firstStorageItem.getReserved(), newReservedValue);
    }

    Order getReleasingOrder(){
        Order order = new Order();
        OrderItem orderItem = new OrderItem();
        Medicine medicine = getMedicineList().get(1);
        orderItem.setMedicine(medicine);
        orderItem.setQuantity(7);
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);
        order.setOrderItems(orderItems);
        return order;
    }

    Storage getStorage(){
        Storage storage = new Storage();
        List<Medicine> medicineList = getMedicineList();
        StorageItem storageItem = new StorageItem();
        storageItem.setId(1l);
        storageItem.setMedicine(medicineList.get(0));
        storageItem.setStock(10);
        storageItem.setReserved(0);

        StorageItem storageItem1 = new StorageItem();
        storageItem1.setId(2l);
        storageItem1.setMedicine(medicineList.get(1));
        storageItem1.setStock(10);
        storageItem1.setReserved(7);

        StorageItem storageItem2 = new StorageItem();
        storageItem2.setId(3l);
        storageItem2.setMedicine(medicineList.get(2));
        storageItem2.setStock(15);
        storageItem2.setReserved(14);

        List<StorageItem> storageItemList = new ArrayList<>();
        storageItemList.add(storageItem);
        storageItemList.add(storageItem1);
        storageItemList.add(storageItem2);
        storage.setStorageItems(storageItemList);

        return storage;
    }

    private List<Medicine> getMedicineList(){
        List<Medicine> medicineList = new ArrayList<>();
        Medicine med1 = new Medicine();
        med1.setId(1l);
        Medicine med2 = new Medicine();
        med2.setId(2l);
        Medicine med3 = new Medicine();
        med3.setId(3l);

        medicineList.add(med1);
        medicineList.add(med2);
        medicineList.add(med3);

        return medicineList;
    }

    private Order getOrderWhoCanFulfill(){
        Order order = new Order();
        List<Medicine> medicineList = getMedicineList();
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(3);
        orderItem.setMedicine(medicineList.get(0));
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setQuantity(3);
        orderItem1.setMedicine(medicineList.get(1));
        OrderItem orderItem2 = new OrderItem();
        orderItem2.setMedicine(medicineList.get(2));
        orderItem2.setQuantity(1);

        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(orderItem);
        orderItemList.add(orderItem1);
        orderItemList.add(orderItem2);
        order.setOrderItems(orderItemList);
        return order;
    }

    private Order getOrderWhoCannotFulfill(){
        Order order = new Order();
        List<Medicine> medicineList = getMedicineList();
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(5);
        orderItem.setMedicine(medicineList.get(0));
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setQuantity(4);
        orderItem1.setMedicine(medicineList.get(1));
        OrderItem orderItem2 = new OrderItem();
        orderItem2.setMedicine(medicineList.get(2));
        orderItem2.setQuantity(2);

        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(orderItem);
        orderItemList.add(orderItem1);
        orderItemList.add(orderItem2);
        order.setOrderItems(orderItemList);
        return order;
    }
}
