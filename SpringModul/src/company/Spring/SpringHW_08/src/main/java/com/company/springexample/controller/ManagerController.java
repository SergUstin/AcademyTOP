package com.company.springexample.controller;

import com.company.springexample.dto.ManagerDto;
import com.company.springexample.service.ManagerCrudServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
@Slf4j
public class ManagerController {

    private final ManagerCrudServiceImpl managerCrudService;

    @GetMapping("/{id}")
    public ResponseEntity<ManagerDto> getById(@PathVariable Long id) {
        try {
            log.info("GET /api/employees/{}", id);
            ManagerDto employee = managerCrudService.getById(id);
            return ResponseEntity.ok(employee);
        } catch (IllegalArgumentException e) {
            log.error("Error while getting employee with id {}", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<Collection<ManagerDto>> getAll() {
        try {
            log.info("GET /api/employees");
            Collection<ManagerDto> employees = managerCrudService.getAll();
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            log.error("Error while getting all employees", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<ManagerDto> create(@RequestBody ManagerDto managerDto) {
        try {
            log.info("POST /api/employees: {}", managerDto);
            managerCrudService.create(managerDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(managerDto);
        } catch (Exception e) {
            log.error("Error while creating employee: {}", managerDto, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity<ManagerDto> update(@RequestBody ManagerDto managerDto) {
        try {
            log.info("PUT /api/employees: {}", managerDto);
            managerCrudService.update(managerDto);
            return ResponseEntity.status(HttpStatus.OK).body(managerDto);
        } catch (Exception e) {
            log.error("Error while updating employee: {}", managerDto, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ManagerDto> deleteById(@PathVariable Long id) {
        try {
            log.info("DELETE /api/employees/{}", id);
            managerCrudService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            log.error("Error while deleting employee with id {}", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
