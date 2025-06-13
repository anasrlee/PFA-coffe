package com.coffee.machine.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "coffe_tables")
public class CoffeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "table_number")
    private String tableNumber;

    @OneToMany(mappedBy = "table")
    private List<Order> orders;
}
