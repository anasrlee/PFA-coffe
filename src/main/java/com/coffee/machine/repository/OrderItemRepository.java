package com.coffee.machine.repository;

import com.coffee.machine.model.ItemStatus;
import com.coffee.machine.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderId(Long orderId);
    List<OrderItem> findByStatus(ItemStatus status);
}
