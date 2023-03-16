package com.hr.microservices.EmployeeMs.EmployeeMs.service;

import com.hr.microservices.EmployeeMs.EmployeeMs.dto.CompanyDto;
import com.hr.microservices.EmployeeMs.EmployeeMs.dto.EmployeeDto;
import com.hr.microservices.EmployeeMs.EmployeeMs.feignClient.CompanyClient;
import com.hr.microservices.EmployeeMs.EmployeeMs.mapper.EmployeeMapper;
import com.hr.microservices.EmployeeMs.EmployeeMs.model.Employee;
import com.hr.microservices.EmployeeMs.EmployeeMs.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

@Autowired
    CompanyClient companyClient;
@Autowired
EmployeeMapper employeeMapper;

@Autowired
EmployeeRepository employeeRepository;



public EmployeeDto registerEmployeeAtCompany(Long companyId, EmployeeDto employeeDto){
    CompanyDto companyById = companyClient.getCompanyById(companyId);
    Object byId = employeeRepository.findById(employeeDto.getId());
    if (!companyId.equals(null) && byId.equals(null)) {
        employeeDto.setCompany(companyById);
        Employee employee = employeeMapper.dtoToEmployee(employeeDto);
        employee.setCompanyId(companyById.getCompRegNum());
        Employee save = employeeRepository.saveAndFlush(employee);
        employeeDto.setId(save.getId());
        return employeeDto;
    }else
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Company does not exist with id: "+companyId+" or employee already exist with id: "+employeeDto.getId());
}

public List<EmployeeDto> getAll(){
    List<CompanyDto> allCompanies = companyClient.findAll();
    List<Employee> allEmployee = employeeRepository.findAll();
    List<EmployeeDto> response = new ArrayList<>();
    for (int i = 0; i < allCompanies.size(); i++) {
        CompanyDto companyDto = allCompanies.get(i);
        Long compRegNum1 = companyDto.getCompRegNum();
        for (int j = 0; j < allEmployee.size(); j++) {
            Employee employee = allEmployee.get(j);
            Long companyId = employee.getCompanyId();
            if (companyId == compRegNum1){
                EmployeeDto employeeDto = employeeMapper.employeeToDto(employee);
                employeeDto.setCompany(companyDto);
                response.add(employeeDto);
            }
        }
    }
    return response;
}
public Employee getEmployeeById(Long id){
    Employee employee = employeeRepository.findById(id).get();
    if (!employee.equals(null)) {
        CompanyDto companyById = companyClient.getCompanyById(employee.getCompanyId());
        employee.setCompanyId(companyById.getCompRegNum());
        return employee;
    }else if (employee.equals(null)) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no such employee with id like: " + id);
    }
        return employee;
}

    public Employee delete(Long id) {
        Employee removedEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, " Employee does not exist with id: " + id));
        employeeRepository.deleteById(id);
        return removedEmployee;
    }

    public List<Employee> findEmployeeBySalary(Integer minSalary) {
        List<Employee> employeeList = employeeRepository.findAll()
                .stream()
                .filter(e -> e.getSalary() > minSalary)
                .collect(Collectors.toList());
        return employeeList;
    }

    public List<Employee> findEmployeeByPosition(String position) {
        return employeeRepository.findByPositionIgnoreCase(position);
    }

    public List<Employee> findEmployeeByNameStartsWith(String name) {
        return employeeRepository.findByNameStartsWithIgnoreCase(name);
    }

    public List<Employee> findEmployeeByStartDateBetween(LocalDateTime start, LocalDateTime end) {
        return employeeRepository.findByStartDateBetween(start, end);
    }

    public Employee findEmployeeById(Long id) {
        Employee employeeById = employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, " Employee does not exist with id: " + id));
        return employeeById;
    }
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void initDbAddEmployeesToCompanies(){

            Employee Alex = new Employee(1L, "Alex", 470000, "Engineer", LocalDateTime.of(2016, 4, 15, 8, 9));
            Employee Geza = new Employee(2L, "Géza", 800000, "Manager", LocalDateTime.of(2019, 7, 15, 8, 9));
            Employee Lili = new Employee(3L, "Lili", 230000, "Assistant", LocalDateTime.of(2022, 1, 3, 8, 10));
            Employee Tibor = new Employee(4L, "Tibor", 450000, "Engineer", LocalDateTime.of(2016, 5, 9, 8, 12));
            Employee József = new Employee(5L, "József", 500000, "Designer", LocalDateTime.of(2019, 7, 15, 8, 9));
            Employee Dénes = new Employee(6L, "Dénes", 450000, "Developer", LocalDateTime.of(2016, 5, 9, 8, 12));
            Employee Armand = new Employee(7L, "Armand", 800000, "Manager", LocalDateTime.of(2011, 3, 15, 9, 11));
            Employee Péter = new Employee(8L, "Péter", 300000, "Engineer", LocalDateTime.of(2022, 1, 3, 8, 10));

            Alex.setCompanyId(1L);
            Geza.setCompanyId(1L);
            Lili.setCompanyId(1L);
            Tibor.setCompanyId(2L);
            József.setCompanyId(2L);
            Dénes.setCompanyId(2L);
            Armand.setCompanyId(3L);
            Péter.setCompanyId(3L);

        employeeRepository.save(Alex);
        employeeRepository.save(Geza);
        employeeRepository.save(Lili);
        employeeRepository.save(Tibor);
        employeeRepository.save(József);
        employeeRepository.save(Dénes);
        employeeRepository.save(Armand);
        employeeRepository.save(Péter);
}
}
