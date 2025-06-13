package com.coffee.machine.service;

import com.coffee.machine.model.*;
import com.coffee.machine.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final IngredientService ingredientService;

    // Single constructor for Spring to autowire
    public OrderItemService(OrderItemRepository orderItemRepository,
                            IngredientService ingredientService) {
        this.orderItemRepository = orderItemRepository;
        this.ingredientService = ingredientService;
    }

    public List<OrderItem> getItemsByOrderId(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    public List<OrderItem> getQueue() {
        return orderItemRepository.findByStatus(ItemStatus.READY); // Changed to findNotReady items
    }

    public OrderItem updateStatus(Long itemId, ItemStatus newStatus) {
        OrderItem item = orderItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        item.setStatus(newStatus);
        return orderItemRepository.save(item);
    }

    public String monitorIngredients() {
        StringBuilder report = new StringBuilder("Ingredient Levels:\n");
        ingredientService.getAllIngredients().forEach(ingredient ->
                report.append(ingredient.getName())
                        .append(": ")
                        .append(ingredient.monitorLevel())
                        .append("\n")
        );
        return report.toString();
    }
}