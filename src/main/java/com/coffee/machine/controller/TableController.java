package com.coffee.machine.controller;

import com.coffee.machine.model.Order;
import com.coffee.machine.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TableController {

    private final OrderService orderService;

    public TableController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/tables/{tableId}/orders")
    public String getOrdersForTable(@PathVariable Long tableId, Model model) {
        List<Order> tableOrders = orderService.getOrdersByTable(tableId);
        model.addAttribute("orders", tableOrders);
        model.addAttribute("tableId", tableId);
        return "redirect:/server/table-orders";  // Your Thymeleaf template
    }
}