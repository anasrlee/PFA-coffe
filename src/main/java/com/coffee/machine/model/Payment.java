package com.coffee.machine.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    private LocalDateTime paymentTime;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
    private Receipt receipt;

    public enum PaymentMethod {
        CASH, CARD, MOBILE
    }
}
