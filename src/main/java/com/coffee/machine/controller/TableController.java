package com.coffee.machine.controller;

import com.coffee.machine.model.Order;
import com.coffee.machine.service.OrderService;
import com.coffee.machine.service.TableService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tables")
public class TableController {

    private final OrderService orderService;
    private final TableService tableService;

    public TableController(OrderService orderService, TableService tableService) {
        this.orderService = orderService;
        this.tableService = tableService;
    }

    @GetMapping("/{tableId}/orders")
    public String getOrdersForTable(@PathVariable Long tableId, Model model) {
        List<Order> tableOrders = orderService.getOrdersByTable(tableId);
        model.addAttribute("orders", tableOrders);
        model.addAttribute("tableId", tableId);
        return "server/table-orders";  // Changed from redirect to direct template render
    }

    @GetMapping
    public String listTables(Model model) {
        model.addAttribute("tables", tableService.getAllTables());
        return "tables/list";
    }
}