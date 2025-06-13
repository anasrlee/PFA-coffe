package com.coffee.machine.controller;

import com.coffee.machine.model.ItemStatus;
import com.coffee.machine.model.OrderItem;
import com.coffee.machine.service.OrderItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {
    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/queue")
    public List<OrderItem> getQueue() {
        return orderItemService.getQueue();
    }

    @PutMapping("/{id}/status")
    public OrderItem updateStatus(@PathVariable Long id,
                                  @RequestParam ItemStatus newStatus) {
        return orderItemService.updateStatus(id, newStatus);
    }

    @GetMapping("/ingredients")
    public String monitorIngredients() {
        return orderItemService.monitorIngredients();
    }

    @GetMapping("/{orderId}/items")
    public List<OrderItem> getOrderItems(@PathVariable Long orderId) {
        return orderItemService.getItemsByOrderId(orderId);
    }
}
