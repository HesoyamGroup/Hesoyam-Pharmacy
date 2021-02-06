package com.hesoyam.pharmacy.pharmacy.service.impl;

import com.hesoyam.pharmacy.medicine.repository.MedicineRepository;
import com.hesoyam.pharmacy.pharmacy.dto.ShowOrderItemDTO;
import com.hesoyam.pharmacy.pharmacy.dto.ShowOrdersDTO;
import com.hesoyam.pharmacy.pharmacy.mapper.OrderMapper;
import com.hesoyam.pharmacy.pharmacy.model.Inventory;
import com.hesoyam.pharmacy.pharmacy.model.Order;
import com.hesoyam.pharmacy.pharmacy.model.OrderItem;
import com.hesoyam.pharmacy.pharmacy.model.OrderStatus;
import com.hesoyam.pharmacy.pharmacy.repository.InventoryRepository;
import com.hesoyam.pharmacy.pharmacy.repository.OrderItemRepository;
import com.hesoyam.pharmacy.pharmacy.repository.OrderRepository;
import com.hesoyam.pharmacy.pharmacy.service.IOrderService;
import com.hesoyam.pharmacy.user.model.Administrator;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private AdministratorRepository administratorRepository;

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<ShowOrdersDTO> getActiveForSupplier(Integer page, User user) {
        List<Order> activeOrders = orderRepository.getUserPendingActionOrders(user.getId(), PageRequest.of(page-1, 6));
        return activeOrders.stream().map(OrderMapper::mapOrderToShowOrderDTO).collect(Collectors.toList());
    }

    @Override
    public Order get(Long id) {
        return orderRepository.getOne(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Order update(Order order) {
        if(order.getId() == null){
            return null;
        }
        return orderRepository.save(order);

    }

    @Override
    public ShowOrdersDTO getBasicOrderInfo(Long id) {
        return OrderMapper.mapOrderToShowOrderDTO(orderRepository.getOne(id));
    }

    @Override
    public Order create(ShowOrdersDTO order, Administrator admin) {
        Administrator administrator = administratorRepository.getOne(admin.getId());
        Order newOrder = new Order();
        List<OrderItem> newOrderItems = getOrderItems(order.getShowOrderItemDTOS());

        newOrder.setAdministrator(administrator);
        newOrder.setDeadLine(order.getDeadLine());
        newOrder.setOrderStatus(OrderStatus.CREATED);
        newOrder.setPharmacy(administrator.getPharmacy());
        newOrder.setOrderItems(newOrderItems);

        Inventory pharmacyInventory = inventoryRepository.getOne(administrator.getPharmacy().getInventory().getId());
        pharmacyInventory.placeOrder(newOrder);

        inventoryRepository.save(pharmacyInventory);
        return orderRepository.save(newOrder);
    }

    private List<OrderItem> getOrderItems(List<ShowOrderItemDTO> showOrderItemDTOS) {
        List<OrderItem> orderItems = new ArrayList<>();
        for(ShowOrderItemDTO orderItem : showOrderItemDTOS){
            OrderItem newOrderItem = new OrderItem();
            newOrderItem.setQuantity(orderItem.getQuantity());
            newOrderItem.setMedicine(medicineRepository.getOne(orderItem.getMedicineId()));
            orderItems.add(newOrderItem);
        }
        return orderItems;
    }
}
