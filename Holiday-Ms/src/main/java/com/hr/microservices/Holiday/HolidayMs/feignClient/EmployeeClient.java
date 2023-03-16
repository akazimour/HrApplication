package com.hr.microservices.Holiday.HolidayMs.feignClient;

import com.hr.microservices.Holiday.HolidayMs.dto.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@FeignClient(name = "employee-ms", url = "http://localhost:8050/employee-ms/", fallback = Fallback.class)
public interface EmployeeClient {

    @GetMapping("api/employees/{id}")
    public Employee getEmployeeById(@PathVariable Long id);
}
@Component
class Fallback implements EmployeeClient{

    @Override
    public Employee getEmployeeById(Long id) {
        throw new ResponseStatusException(HttpStatus.GATEWAY_TIMEOUT,"Employee service is taking too long to respond or it is down! Please try again later...");

    }
}
