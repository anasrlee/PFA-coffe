package com.coffee.machine.repository;

import com.coffee.machine.model.CoffeTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<CoffeTable, Long> {
}
