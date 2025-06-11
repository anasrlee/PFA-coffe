package com.coffee.machine.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Entity
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "server")
    private List<Order> servedOrders;

    private boolean active;

}