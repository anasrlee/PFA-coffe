package com.coffee.machine.controller;


import com.coffee.machine.model.*;
import com.coffee.machine.service.CashierService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/cashier")
public class CashierController {

    private final CashierService cashierService;

    public CashierController(CashierService cashierService) {
        this.cashierService = cashierService;
    }

    @GetMapping("/completed-orders")
    public String viewCompletedOrders(Model model) {
        model.addAttribute("orders", cashierService.getCompletedOrders());
        return "cashier/completed-orders";
    }

    @PostMapping("/process-payment")
    public String processPayment(@RequestParam Long orderId,
                                 @RequestParam double amount,
                                 @RequestParam Payment.PaymentMethod method,
                                 Model model) {
        Payment payment = cashierService.processPayment(orderId, amount, method);
        model.addAttribute("payment", payment);
        model.addAttribute("receipt", payment.getReceipt());
        return "cashier/payment-confirmation";
    }

    @GetMapping("/order-history")
    public String manageOrderHistory(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            Model model) {

        if (startDate == null) startDate = LocalDateTime.now().minusDays(7);
        if (endDate == null) endDate = LocalDateTime.now();

        model.addAttribute("payments",
                cashierService.getOrderHistory(startDate, endDate));
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        return "cashier/order-history";
    }
}