package com.coffee.machine.service;

import com.coffee.machine.model.CoffeTable;
import com.coffee.machine.repository.TableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService {

    private final TableRepository tableRepository;

    public TableService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public List<CoffeTable> getAllTables() {
        return tableRepository.findAll();
    }

    public CoffeTable getTableById(Long id) {
        return tableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Table not found"));
    }
}