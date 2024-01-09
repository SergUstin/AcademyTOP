package com.compony.springexample.service;

import com.compony.springexample.dto.EmployeeDto;
import com.compony.springexample.model.Employee;
import com.compony.springexample.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeCrudServiceImpl implements CRUDService<EmployeeDto> {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto getById(Integer id) {
        log.info("Get by ID: " + id);
        Employee employee = employeeRepository.findById(id).orElseThrow();
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
        employeeRepository.deleteById(Math.toIntExact(id));
    }

    public static EmployeeDto mapToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employeeDto.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setPosition(employee.getPosition());
        return employeeDto;
    }

    public static Employee mapToEntity(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setName(employeeDto.getName());
        employee.setPosition(employeeDto.getPosition());
        return employee;
    }
}
