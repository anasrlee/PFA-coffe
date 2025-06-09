package com.coffee.machine.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "customer_orders")  // Fixed annotation from @CoffeTable to @Table
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Column(nullable = false)
    private LocalDateTime orderTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status = OrderStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    private CoffeTable table;  // This requires the CoffeeTable entity to exist

    @ManyToOne
    @JoinColumn(name = "server_id", nullable = false)
    private User server;
}