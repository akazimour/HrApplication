package com.hr.microservices.CompanyMs.CompanyMs.feignClient;

import com.hr.microservices.CompanyMs.CompanyMs.dto.Employee;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "employee-ms",url = "http://localhost:8050/employee-ms/")
public interface EmployeeClient {

    @GetMapping("api/employees/sum")
    public List<Employee> getAll();

}


