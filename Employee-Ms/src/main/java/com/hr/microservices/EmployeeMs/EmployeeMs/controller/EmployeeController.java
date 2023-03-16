package com.hr.microservices.EmployeeMs.EmployeeMs.controller;

import com.hr.microservices.EmployeeMs.EmployeeMs.dto.CompanyDto;
import com.hr.microservices.EmployeeMs.EmployeeMs.dto.EmployeeDto;
import com.hr.microservices.EmployeeMs.EmployeeMs.feignClient.CompanyClient;
import com.hr.microservices.EmployeeMs.EmployeeMs.mapper.EmployeeMapper;
import com.hr.microservices.EmployeeMs.EmployeeMs.model.Employee;
import com.hr.microservices.EmployeeMs.EmployeeMs.repository.EmployeeRepository;
import com.hr.microservices.EmployeeMs.EmployeeMs.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    @Autowired
   EmployeeService employeeService;
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CompanyClient companyClient;

    @Autowired
    EmployeeMapper employeeMapper;

    static final String COMPANY_FILTER = "companyFilter";

    @PostMapping("/company/{id}")
    public EmployeeDto insertEmployeeWithCompanyId(@PathVariable Long id, @RequestBody EmployeeDto employeeDto){
       return employeeService.registerEmployeeAtCompany(id, employeeDto);
    }
    @GetMapping
    public List<EmployeeDto> getAllEmployee(){
        return employeeService.getAll();
    }

    @GetMapping("/sum")
    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name = COMPANY_FILTER,fallbackMethod = "fallback")
    public EmployeeDto getEmployeeById(@PathVariable Long id){
        Employee employeeById = employeeService.getEmployeeById(id);
        CompanyDto companyById = companyClient.getCompanyById(employeeById.getCompanyId());
        EmployeeDto employeeDto = employeeMapper.employeeToDto(employeeById);
        employeeDto.setCompany(companyById);
        return employeeDto;
    }

    @GetMapping("/job/{position}")
    public List<EmployeeDto> getByPosition(@PathVariable String position) {
        List<Employee> byPosition = employeeService.findEmployeeByPosition(position);
        if (byPosition != null) {
            return employeeMapper.employeesToDtos(byPosition);
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " No result with such position like: " + position);
    }

    @GetMapping("/starts/{name}")
    public List<EmployeeDto> getByName(@PathVariable String name) {
        List<Employee> byName = employeeService.findEmployeeByNameStartsWith(name);
        if (name != null) {
            return employeeMapper.employeesToDtos(byName);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " No result with such letter or name like: " + name);
        }
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @GetMapping("/date")
    public List<EmployeeDto> getDateBetween(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        if (start == null || end == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " No result with such dates like: " + start + " and " + end);
        } else if (start.isEqual(end)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " The two dates are equal: " + start + " and " + end);
        } else if (start.isAfter(end)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " Start date has to be earlier than end date!: " + start + " and " + end);
        } else {
            return employeeMapper.employeesToDtos(employeeService.findEmployeeByStartDateBetween(start, end));
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> modifyEmployee(@RequestBody @Valid EmployeeDto employeeDto, @PathVariable Long id) {
        if (employeeDto.getId() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provided Id-s are not same: " + employeeDto.getId() + " and " + id);
        }
        Employee byId = employeeService.findEmployeeById(id);
        if (byId != null) {
            Employee employee = employeeService.save(employeeMapper.dtoToEmployee(employeeDto));
            return ResponseEntity.ok(employeeMapper.employeeToDto(employee));
        } else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " Employee does not exist with id: " + id);
    }
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id does not exist!");
        } else
            employeeService.delete(id);
    }


    @RequestMapping("/fallback")
    public EmployeeDto fallback(Exception e){
        throw new ResponseStatusException(HttpStatus.GATEWAY_TIMEOUT,"Company service is taking too long to respond or it is down! Please try again later...");

    }








}
