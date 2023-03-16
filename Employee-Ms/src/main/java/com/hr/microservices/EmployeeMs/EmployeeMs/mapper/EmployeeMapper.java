package com.hr.microservices.EmployeeMs.EmployeeMs.mapper;


import com.hr.microservices.EmployeeMs.EmployeeMs.dto.EmployeeDto;
import com.hr.microservices.EmployeeMs.EmployeeMs.model.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    List<EmployeeDto> employeesToDtos(List<Employee> employee);
    EmployeeDto employeeToDto(Employee employee);
    Employee dtoToEmployee(EmployeeDto employeeDto);

    List<Employee> dtosToEmployees(List<EmployeeDto> employeeDto);
}
