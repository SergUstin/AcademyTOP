package com.compony.springexample.service;

import com.compony.springexample.dto.EmployeeDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;


@Service
public class EmployeeCrudServiceImpl implements CRUDService<EmployeeDto> {

    private List<EmployeeDto> employeeList = new ArrayList<>();

    @Override
    public EmployeeDto getById(Long id) {
        return employeeList.stream()
                .filter(em -> em.getId().equals(id))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Collection<EmployeeDto> getAll() {
        return employeeList.stream().toList();
    }

    @Override
    public void create(EmployeeDto item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null.");
        }

        long nextId = employeeList.stream()
                .mapToLong(EmployeeDto::getId)
                .max()
                .orElse(0) + 1;

        item.setId(nextId);
        employeeList.add(item);
    }

    @Override
    public void updateById(Long id, EmployeeDto item) {
        if (employeeList.stream().noneMatch(e -> e.getId().equals(id))) {
            throw new IllegalArgumentException("Employee with id " + id + " does not exist.");
        }

        item.setId(id);

        int index = IntStream.range(0, employeeList.size())
                .filter(i -> employeeList.get(i).getId().equals(id))
                .findFirst()
                .getAsInt();

        employeeList.set(index, item);
    }

    @Override
    public void deleteById(Long id) {
        if (employeeList.stream().noneMatch(e -> e.getId().equals(id))) {
            throw new IllegalArgumentException("Employee with id " + id + " does not exist.");
        }

        employeeList.removeIf(em -> em.getId().equals(id));
    }
}
