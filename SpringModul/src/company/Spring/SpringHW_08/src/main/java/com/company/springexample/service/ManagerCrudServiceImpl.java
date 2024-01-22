package com.company.springexample.service;

import com.company.springexample.dto.ManagerDto;
import com.company.springexample.model.Manager;
import com.company.springexample.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManagerCrudServiceImpl implements CRUDService<ManagerDto> {

    private final ManagerRepository managerRepository;

    @Override
    public ManagerDto getById(Long id) {
        log.info("Get by id " + id);
        return mapToDto(managerRepository.findById(id).orElseThrow());
    }

    @Override
    public Collection<ManagerDto> getAll() {
        return managerRepository.findAll()
                .stream()
                .map(ManagerCrudServiceImpl::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void create(ManagerDto item) {
        managerRepository.save(mapToEntity(item));

    }

    @Override
    public void update(ManagerDto item) {
        managerRepository.save(mapToEntity(item));
    }

    @Override
    public void deleteById(Long id) {
        managerRepository.deleteById(id);
    }

    public static Manager mapToEntity(ManagerDto managerDto) {
        Manager manager = new Manager();
        manager.setId(managerDto.getId());
        manager.setFirstName(managerDto.getFirstName());
        manager.setLastName(managerDto.getLastName());
        manager.setEmployees(managerDto.getEmployees()
                .stream()
                .map(EmployeeCrudServiceImpl::mapToEntity)
                .collect(Collectors.toList()));
        return manager;
    }

    public static ManagerDto mapToDto(Manager manager) {
        ManagerDto managerDto = new ManagerDto();
        managerDto.setId(manager.getId());
        managerDto.setFirstName(manager.getFirstName());
        managerDto.setLastName(manager.getLastName());
        managerDto.setEmployees(manager.getEmployees()
                .stream()
                .map(EmployeeCrudServiceImpl::mapToDto)
                .collect(Collectors.toList()));

        return managerDto;
    }
}
