package com.compony.springexample.service;

import com.compony.springexample.dto.EmployeeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeCrudServiceImplTest {

    private EmployeeCrudServiceImpl employeeCrudService;

    @BeforeEach
    public void setUp() {
        employeeCrudService = new EmployeeCrudServiceImpl();
    }

    @Test
    public void testGetById() {
        EmployeeDto employee1 = new EmployeeDto(1L,
                "Jane",
                "Smith",
                "Java-Developer",
                "Fin-Tech",
                new BigDecimal(7000));
        employeeCrudService.create(employee1);

        EmployeeDto employee2 = new EmployeeDto(2L,
                "John",
                "Doe",
                "Java-Developer",
                "Fin-Tech",
                new BigDecimal(5000));
        employeeCrudService.create(employee2);

        EmployeeDto result = employeeCrudService.getById(1L);

        assertEquals(employee1, result);
    }

    @Test
    public void testGetAll() {
        EmployeeDto employee1 = new EmployeeDto(1L,
                "John",
                "Doe",
                "Java-Developer",
                "Fin-Tech",
                new BigDecimal(5000));
        employeeCrudService.create(employee1);

        EmployeeDto employee2 = new EmployeeDto(2L,
                "Jane",
                "Smith",
                "Java-Developer",
                "Fin-Tech",
                new BigDecimal(7000));
        employeeCrudService.create(employee2);

        Collection<EmployeeDto> employees = employeeCrudService.getAll();

        assertEquals(2, employees.size());
        assertTrue(employees.contains(employee1));
        assertTrue(employees.contains(employee2));
    }

    @Test
    public void testCreate() {
        EmployeeDto employee = new EmployeeDto(1L,
                "Jane",
                "Smith",
                "Java-Developer",
                "Fin-Tech",
                new BigDecimal(7000));
        employeeCrudService.create(employee);

        assertNotNull(employeeCrudService.getById(1L));
    }

    @Test
    public void testUpdateById() {
        EmployeeDto employee = new EmployeeDto(1L,
                "Jane",
                "Smith",
                "Java-Developer",
                "Fin-Tech",
                new BigDecimal(7000));
        employeeCrudService.create(employee);

        EmployeeDto updateEmployee = new EmployeeDto(1L,
                "Jane",
                "Smith",
                "Java-Developer",
                "Fin-Tech",
                new BigDecimal(7000));
        employeeCrudService.updateById(1L, updateEmployee);

        EmployeeDto result = employeeCrudService.getById(1L);

        assertEquals(updateEmployee, result);
    }

    @Test
    public void testDeleteById() {
        EmployeeDto employee = new EmployeeDto(1L,
                "Jane",
                "Smith",
                "Java-Developer",
                "Fin-Tech",
                new BigDecimal(7000));
        employeeCrudService.create(employee);

        employeeCrudService.deleteById(1L);

        assertThrows(IllegalArgumentException.class, () -> employeeCrudService.getById(1L));
    }

}