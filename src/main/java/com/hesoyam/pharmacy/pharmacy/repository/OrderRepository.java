package com.hesoyam.pharmacy.pharmacy.repository;

import com.hesoyam.pharmacy.pharmacy.model.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT ord FROM Order ord LEFT JOIN FETCH ord.orderItems WHERE (SELECT COUNT(offer) FROM ord.offers offer WHERE offer.supplier.id=:userId AND offer.offerStatus ='CREATED') = 0 " +
            "AND ord.orderStatus = 'CREATED'AND ord.deadLine >= :todayDate")
    List<Order> getUserPendingActionOrders(@Param("userId")Long id, @Param("todayDate") LocalDateTime localDateTime, Pageable pageable);

    List<Order> getAllByPharmacy_Id(Long pharmacyId);
}
