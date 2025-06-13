package com.coffee.machine.service;

import com.coffee.machine.model.*;
import com.coffee.machine.repository.OrderRepository;
import com.coffee.machine.repository.PaymentRepository;
import com.coffee.machine.repository.ReceiptRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CashierService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final ReceiptRepository receiptRepository;

    public CashierService(OrderRepository orderRepository,
                          PaymentRepository paymentRepository,
                          ReceiptRepository receiptRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
        this.receiptRepository = receiptRepository;
    }

    public List<Order> getCompletedOrders() {
        return orderRepository.findByStatus(OrderStatus.COMPLETED);
    }

    public Payment processPayment(Long orderId, double amount, Payment.PaymentMethod method) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus() != OrderStatus.COMPLETED) {
            throw new RuntimeException("Order not completed");
        }

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(amount);
        payment.setMethod(method);
        payment.setPaymentTime(LocalDateTime.now());

        Payment savedPayment = paymentRepository.save(payment);

        // Générer le reçu
        Receipt receipt = Receipt.generateReceipt(savedPayment);
        receiptRepository.save(receipt);

        return savedPayment;
    }

    public List<Payment> getOrderHistory(LocalDateTime startDate, LocalDateTime endDate) {
        return paymentRepository.findByPaymentTimeBetween(startDate, endDate);
    }
}
