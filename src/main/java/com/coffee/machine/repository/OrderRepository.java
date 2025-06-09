package com.coffee.machine.repository;

import com.coffee.machine.model.Order;
import com.coffee.machine.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByTableId(Long tableId);
}