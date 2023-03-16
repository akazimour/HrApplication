package com.hr.microservices.CompanyMs.CompanyMs.controller;

import com.hr.microservices.CompanyMs.CompanyMs.dto.CompanyDto;
import com.hr.microservices.CompanyMs.CompanyMs.mapper.CompanyMapper;
import com.hr.microservices.CompanyMs.CompanyMs.service.CompanyService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/companies")
public class CompanyController {

    @Autowired
    CompanyService companyService;
    @Autowired
    CompanyMapper companyMapper;

    static final String EMPLOYEE_FILTER = "employeeFilter";

    @GetMapping
    public List<CompanyDto> findAll(){
        return companyMapper.companiesToDtos(companyService.findAllCompany());
    }

    @GetMapping("/{id}")
    public CompanyDto getCompanyById(@PathVariable Long id){
       return companyMapper.companyToDto(companyService.findCompanyById(id));
    }

    @GetMapping("/employees")
    @CircuitBreaker(name = EMPLOYEE_FILTER,fallbackMethod = "fallback")
    public List<CompanyDto> findAllWithEmployees(HttpServletRequest request) throws Exception{
        return companyService.findAllCompanyWithEmployees();
    }

    @PostMapping
    public CompanyDto addNewCompany(@RequestBody CompanyDto companyDto){
        return companyMapper.companyToDto(companyService.save(companyMapper.dtoToCompany(companyDto)));
    }

    @RequestMapping("/fallback")
    public List<CompanyDto> fallback(Exception e){
        throw new ResponseStatusException(HttpStatus.GATEWAY_TIMEOUT,"Employee service is taking too long to respond or it is down! Please try again later...");

    }

}
