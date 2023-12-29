package com.compony.springexample.controller;

import com.compony.springexample.dto.EmployeeDto;
import com.compony.springexample.service.EmployeeCrudServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeCrudServiceImpl employeeService;

    public EmployeeController(EmployeeCrudServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable Long id) {
        try {
            EmployeeDto employee = employeeService.getById(id);
            return ResponseEntity.ok(employee);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<Collection<EmployeeDto>> getAll() {
        try {
            Collection<EmployeeDto> employees = employeeService.getAll();
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto employeeDto) {
        try {
            employeeService.create(employeeDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateById(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        try {
            employeeService.updateById(id, employeeDto);
            return ResponseEntity.status(HttpStatus.OK).body(employeeDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeDto> deleteById(@PathVariable Long id) {
        try {
            employeeService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
