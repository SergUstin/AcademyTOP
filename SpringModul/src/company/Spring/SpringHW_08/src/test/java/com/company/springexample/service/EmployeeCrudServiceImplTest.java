package com.company.springexample.service;

import com.company.springexample.dto.EmployeeDto;
import com.company.springexample.model.Employee;
import com.company.springexample.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeCrudServiceImplTest {

    private EmployeeCrudServiceImpl employeeCrudService;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        employeeCrudService = new EmployeeCrudServiceImpl(employeeRepository);
    }

    @Test
    public void testGetById_ExistingId_ReturnsEmployeeDto() {
        // Arrange
        long id = 1L;
        Employee employee = new Employee();
        employee.setId(id);
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        // Act
        EmployeeDto result = employeeCrudService.getById(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    public void testGetById_NonExistingId_ThrowsNoSuchElementException() {
        // Arrange
        long id = 1L;
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> employeeCrudService.getById(id));
    }

    @Test
    public void testGetAll_ReturnsCollectionOfEmployeeDto() {
        // Arrange
        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        when(employeeRepository.findAll()).thenReturn(employees);

        // Act
        Collection<EmployeeDto> result = employeeCrudService.getAll();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    public void testCreate_ValidEmployeeDto_CallsSaveMethod() {
        // Arrange
        EmployeeDto employeeDto = new EmployeeDto();

        // Act
        employeeCrudService.create(employeeDto);

        // Assert
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    public void testUpdate_ValidEmployeeDto_CallsSaveMethod() {
        // Arrange
        EmployeeDto employeeDto = new EmployeeDto();

        // Act
        employeeCrudService.update(employeeDto);

        // Assert
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    public void testDeleteById_ExistingId_CallsDeleteByIdMethod() {
        // Arrange
        long id = 1L;

        // Act
        employeeCrudService.deleteById(id);

        // Assert
        verify(employeeRepository, times(1)).deleteById(id);
    }

}