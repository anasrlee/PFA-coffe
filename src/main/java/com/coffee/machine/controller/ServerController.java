package com.coffee.machine.controller;

import com.coffee.machine.model.Order;
import com.coffee.machine.model.OrderStatus;
import com.coffee.machine.model.User;
import com.coffee.machine.service.OrderService;
import com.coffee.machine.service.TableService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/server")
public class ServerController {

    private final OrderService orderService;
    private final TableService tableService;

    public ServerController(OrderService orderService, TableService tableService) {
        this.orderService = orderService;
        this.tableService = tableService;
    }

    // Interface pour voir toutes les tables
    @GetMapping("/tables")
    public String serverInterface(Model model) {
        model.addAttribute("tables", tableService.getAllTables());
        return "server/tables";
    }

    // Prendre une commande
    @PostMapping("/take-order")
    public String takeOrder(@RequestParam Long tableId,
                            @RequestParam String orderDesc,
                            HttpSession session) {
        User server = (User) session.getAttribute("user");
        orderService.createOrder(tableId, orderDesc, server);
        return "redirect:/server/tables";
    }

    // Dashboard des commandes classées par statut
    @GetMapping("/orders")
    public String viewOrdersDashboard(Model model) {
        model.addAttribute("pendingOrders", orderService.getOrdersByStatus(OrderStatus.PENDING));
        model.addAttribute("inProgressOrders", orderService.getOrdersByStatus(OrderStatus.IN_PROGRESS));
        model.addAttribute("completedOrders", orderService.getOrdersByStatus(OrderStatus.COMPLETED));
        model.addAttribute("deliveredOrders", orderService.getOrdersByStatus(OrderStatus.DELIVERED));
        return "server/orders";
    }

    // Mise à jour du statut
    @PostMapping("/orders/update-status/{orderId}")
    public String updateOrderStatus(@PathVariable Long orderId,
                                    @RequestParam OrderStatus newStatus) {
        orderService.updateOrderStatus(orderId, newStatus);
        return "redirect:/server/orders";
    }
}