package com.company.springexample.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManagerDto {
    Long id;
    String firstName;
    String lastName;
    List<EmployeeDto> employees;

}
