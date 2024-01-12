package com.company.springexample.service;

import com.company.springexample.dto.EmployeeDto;
import com.company.springexample.model.Employee;
import com.company.springexample.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeCrudServiceImpl implements CRUDService<EmployeeDto> {
    private final EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto getById(Long id) {
        log.info("Get by ID: " + id);
        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));
        return mapToDto(employee);
    }
    @Override
    public Collection<EmployeeDto> getAll() {
        log.info("Get all");
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeCrudServiceImpl::mapToDto)
                .collect(Collectors.toList());
    }
    @Override
    public void create(EmployeeDto item) {
        log.info("Create");
        Employee employee = mapToEntity(item);
        employeeRepository.save(employee);
    }
    @Override
    public void update(EmployeeDto item) {
        log.info("Update");
        Employee employee = mapToEntity(item);
        employeeRepository.save(employee);
    }
    @Override
    public void deleteById(Long id) {
        log.info("Delete by ID: " + id);
        employeeRepository.deleteById(id);
    }
    public static EmployeeDto mapToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setPosition(employee.getPosition());
        employeeDto.setDepartment(employee.getDepartment());
        employeeDto.setSalary(employee.getSalary());
        return employeeDto;
    }
    public static Employee mapToEntity(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setPosition(employeeDto.getPosition());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setSalary(employeeDto.getSalary());
        return employee;
    }
}
