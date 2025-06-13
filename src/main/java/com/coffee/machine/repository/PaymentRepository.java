package com.coffee.machine.repository;

import com.coffee.machine.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByOrderId(Long orderId);
    List<Payment> findByPaymentTimeBetween(LocalDateTime start, LocalDateTime end);
}
