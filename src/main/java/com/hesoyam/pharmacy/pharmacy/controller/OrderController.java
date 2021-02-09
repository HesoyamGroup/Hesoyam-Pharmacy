package com.hesoyam.pharmacy.pharmacy.controller;

import com.hesoyam.pharmacy.pharmacy.dto.OrderDTO;
import com.hesoyam.pharmacy.pharmacy.dto.ShowOrdersDTO;
import com.hesoyam.pharmacy.pharmacy.model.Order;
import com.hesoyam.pharmacy.pharmacy.service.IOrderService;
import com.hesoyam.pharmacy.user.model.Administrator;
import com.hesoyam.pharmacy.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @GetMapping("")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<List<OrderDTO>> getAllByAdministrator(@AuthenticationPrincipal User user){
        try{
            List<Order> orders = orderService.getAllByAdministratorPharmacy(user);
            List<OrderDTO> ordersDTO = new ArrayList<>();
            orders.forEach(order -> ordersDTO.add(new OrderDTO(order)));
            return ResponseEntity.ok().body(ordersDTO);
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<OrderDTO> updateDeadline(@AuthenticationPrincipal User user, @RequestBody OrderDTO orderDTO){
        try{
            Order updatedOrder = orderService.updateDeadline(user, orderDTO);
            return ResponseEntity.ok().body(new OrderDTO(updatedOrder));
        } catch (IllegalAccessException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        } catch(IllegalStateException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<Void> deleteOrder(@AuthenticationPrincipal User user, @PathVariable Long id){
        try{
            orderService.delete(user, id);
            return ResponseEntity.ok().build();
        }catch (IllegalAccessException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/active")
    @Secured("ROLE_SUPPLIER")
    public ResponseEntity<List<ShowOrdersDTO>> getAllActive(@RequestParam(required = false)Integer page, @AuthenticationPrincipal User user){
        page = (page == null ? 1 : page);
        return ResponseEntity.ok(orderService.getActiveForSupplier(page, user));
    }

    @GetMapping("/id/{id}")
    @Secured("ROLE_SUPPLIER") //note: Add more roles if neccessary.
    public ResponseEntity<ShowOrdersDTO> getBasicOrderInfo(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(orderService.getBasicOrderInfo(id));
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShowOrdersDTO> create(@AuthenticationPrincipal Administrator administrator, @RequestBody ShowOrdersDTO order){
        try{
            Order newOrder = orderService.create(order, administrator);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ShowOrdersDTO(newOrder));
        } catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }


    }
}
