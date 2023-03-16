package com.hr.microservices.EmployeeMs.EmployeeMs.feignClient;

import com.hr.microservices.EmployeeMs.EmployeeMs.dto.CompanyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@FeignClient(name = "company-ms",url = "http://localhost:8050/company-ms/",fallback = Fallback.class)
public interface CompanyClient {

    @GetMapping("api/companies/{id}")
    public CompanyDto getCompanyById(@PathVariable Long id);

    @GetMapping("api/companies")
    public List<CompanyDto> findAll();


}

@Component
class Fallback implements CompanyClient{

    @Override
    public CompanyDto getCompanyById(Long id) {
        throw new ResponseStatusException(HttpStatus.GATEWAY_TIMEOUT,"Company service is taking too long to respond or it is down. Please try again later...");
    }

    @Override
    public List<CompanyDto> findAll(){
        throw new ResponseStatusException(HttpStatus.GATEWAY_TIMEOUT,"Company service is taking too long to respond or it is down. Please try again later...");

    }
}
