package com.coffee.machine.service;

import com.coffee.machine.model.*;
import com.coffee.machine.repository.OrderRepository;
import com.coffee.machine.repository.TableRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final TableRepository tableRepository;

    public OrderService(OrderRepository orderRepository, TableRepository tableRepository) {
        this.orderRepository = orderRepository;
        this.tableRepository = tableRepository;
    }

    public List<Order> getOrdersByTable(Long tableId) {
        return orderRepository.findByTableId(tableId);
    }

    public Order createOrder(Long tableId, String description, User server) {
        CoffeTable table = tableRepository.findById(tableId)
                .orElseThrow(() -> new EntityNotFoundException("Table not found with id: " + tableId));

        Order order = new Order();
        order.setTable(table);
        order.setDescription(description);
        order.setOrderTime(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.setServer(server);

        return orderRepository.save(order);
    }

    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderId));
        order.setStatus(newStatus);
        orderRepository.save(order);
    }
}