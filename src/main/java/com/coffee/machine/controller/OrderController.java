package com.coffee.machine.controller;

import com.coffee.machine.model.Order;
import com.coffee.machine.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Voir toutes les commandes
    @GetMapping
    public String showAllOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders/all-orders"; // Par exemple: templates/orders/all-orders.html
    }

    // Voir les commandes par table
    @GetMapping("/table/{tableId}")
    public String showOrdersByTable(@PathVariable Long tableId, Model model) {
        model.addAttribute("orders", orderService.getOrdersByTable(tableId));
        model.addAttribute("tableId", tableId);
        return "orders/by-table"; // Par exemple: templates/orders/by-table.html
    }
}

