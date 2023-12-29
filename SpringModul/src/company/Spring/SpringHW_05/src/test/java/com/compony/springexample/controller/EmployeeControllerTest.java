package com.compony.springexample.controller;

import com.compony.springexample.dto.EmployeeDto;
import com.compony.springexample.service.EmployeeCrudServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    private final EmployeeCrudServiceImpl employeeService = mock(EmployeeCrudServiceImpl.class);
    private final EmployeeController employeeController = new EmployeeController(employeeService);

    @Test
    void getById_shouldReturnEmployeeDto_whenIdExists() {
        // Arrange
        Long id = 1L;
        EmployeeDto employeeDto = new EmployeeDto(id,
                "John",
                "Doe",
                "Java-Developer",
                "Fin-Tech",
                new BigDecimal(5000));

        when(employeeService.getById(id)).thenReturn(employeeDto);

        // Act
        ResponseEntity<EmployeeDto> response = employeeController.getById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeDto, response.getBody());

        verify(employeeService, times(1)).getById(id);
    }

    @Test
    void getById_shouldReturnNotFound_whenIdDoesNotExist() {
        // Arrange
        Long id = 1L;

        when(employeeService.getById(id)).thenThrow(new IllegalArgumentException());

        // Act
        ResponseEntity<EmployeeDto> response = employeeController.getById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(employeeService, times(1)).getById(id);
    }

    @Test
    void getAll_shouldReturnAllEmployees() {
        // Arrange
        Collection<EmployeeDto> employees = Arrays.asList(
                new EmployeeDto(1L,
                        "John",
                        "Doe",
                        "Java-Developer",
                        "Fin-Tech",
                        new BigDecimal(5000)),
                new EmployeeDto(2L,
                        "Jane",
                        "Smith",
                        "Java-Developer",
                        "Fin-Tech",
                        new BigDecimal(7000))
        );

        when(employeeService.getAll()).thenReturn(employees);

        // Act
        ResponseEntity<Collection<EmployeeDto>> response = employeeController.getAll();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employees, response.getBody());

        verify(employeeService, times(1)).getAll();
    }

    @Test
    void getAll_shouldReturnInternalServerError_whenExceptionOccurs() {
        // Arrange
        when(employeeService.getAll()).thenThrow(new IllegalArgumentException());

        // Act
        ResponseEntity<Collection<EmployeeDto>> response = employeeController.getAll();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());

        verify(employeeService, times(1)).getAll();
    }

    @Test
    void create_shouldReturnCreatedEmployee() {
        // Arrange
        EmployeeDto employeeDto = new EmployeeDto(1L,
                "Jane",
                "Smith",
                "Java-Developer",
                "Fin-Tech",
                new BigDecimal(7000));

        // Act
        ResponseEntity<EmployeeDto> response = employeeController.create(employeeDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(employeeDto, response.getBody());

        verify(employeeService, times(1)).create(employeeDto);
    }

    @Test
    void create_shouldReturnInternalServerError_whenExceptionOccurs() {
        // Arrange
        EmployeeDto employeeDto = new EmployeeDto(1L,
                "Jane",
                "Smith",
                "Java-Developer",
                "Fin-Tech",
                new BigDecimal(7000));

        doThrow(new IllegalArgumentException()).when(employeeService).create(employeeDto);

        // Act
        ResponseEntity<EmployeeDto> response = employeeController.create(employeeDto);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());

        verify(employeeService, times(1)).create(employeeDto);
    }

    @Test
    void updateById_shouldReturnUpdatedEmployee_whenIdExists() {
        // Arrange
        Long id = 1L;
        EmployeeDto employeeDto = new EmployeeDto(id,
                "Jane",
                "Smith",
                "Java-Developer",
                "Fin-Tech",
                new BigDecimal(7000));

        // Act
        ResponseEntity<EmployeeDto> response = employeeController.updateById(id, employeeDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeDto, response.getBody());

        verify(employeeService, times(1)).updateById(id, employeeDto);
    }

    @Test
    void updateById_shouldReturnNotFound_whenIdDoesNotExist() {
        // Arrange
        Long id = 1L;
        EmployeeDto employeeDto = new EmployeeDto(id,
                "Jane",
                "Smith",
                "Java-Developer",
                "Fin-Tech",
                new BigDecimal(7000));

        doThrow(new IllegalArgumentException()).when(employeeService).updateById(id, employeeDto);

        // Act
        ResponseEntity<EmployeeDto> response = employeeController.updateById(id, employeeDto);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(employeeService, times(1)).updateById(id, employeeDto);
    }

    @Test
    void deleteById_shouldReturnNoContent_whenIdExists() {
        // Arrange
        Long id = 1L;

        // Act
        ResponseEntity<EmployeeDto> response = employeeController.deleteById(id);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(employeeService, times(1)).deleteById(id);
    }

    @Test
    void deleteById_shouldReturnNotFound_whenIdDoesNotExist() {
        // Arrange
        Long id = 1L;

        doThrow(new IllegalArgumentException()).when(employeeService).deleteById(id);

        // Act
        ResponseEntity<EmployeeDto> response = employeeController.deleteById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(employeeService, times(1)).deleteById(id);
    }

}