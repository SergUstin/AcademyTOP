package com.company.springexample.controller;

import com.company.springexample.dto.EmployeeDto;
import com.company.springexample.service.EmployeeCrudServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
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
            log.info("GET /api/employees/{}", id);
            EmployeeDto employee = employeeService.getById(id);
            return ResponseEntity.ok(employee);
        } catch (IllegalArgumentException e) {
            log.error("Error while getting employee with id {}", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<Collection<EmployeeDto>> getAll() {
        try {
            log.info("GET /api/employees");
            Collection<EmployeeDto> employees = employeeService.getAll();
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            log.error("Error while getting all employees", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto employeeDto) {
        try {
            log.info("POST /api/employees: {}", employeeDto);
            employeeService.create(employeeDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeDto);
        } catch (Exception e) {
            log.error("Error while creating employee: {}", employeeDto, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity<EmployeeDto> update(@RequestBody EmployeeDto employeeDto) {
        try {
            log.info("PUT /api/employees: {}", employeeDto);
            employeeService.update(employeeDto);
            return ResponseEntity.status(HttpStatus.OK).body(employeeDto);
        } catch (Exception e) {
            log.error("Error while updating employee: {}", employeeDto, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeDto> deleteById(@PathVariable Long id) {
        try {
            log.info("DELETE /api/employees/{}", id);
            employeeService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            log.error("Error while deleting employee with id {}", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
