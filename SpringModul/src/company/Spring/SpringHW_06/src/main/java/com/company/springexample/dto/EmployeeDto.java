package com.company.springexample.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    Long id;
    String firstName;
    String lastName;
    String position;
    String department;
    BigDecimal salary;
}
