package com.hr.microservices.CompanyMs.CompanyMs.service;

import com.hr.microservices.CompanyMs.CompanyMs.dto.CompanyDto;
import com.hr.microservices.CompanyMs.CompanyMs.dto.Employee;
import com.hr.microservices.CompanyMs.CompanyMs.feignClient.EmployeeClient;
import com.hr.microservices.CompanyMs.CompanyMs.mapper.CompanyMapper;
import com.hr.microservices.CompanyMs.CompanyMs.model.Company;
import com.hr.microservices.CompanyMs.CompanyMs.repository.CompanyRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    EmployeeClient employeeClient;
    @Autowired
    CompanyMapper companyMapper;

    public Company findCompanyById(Long id){
       return companyRepository.findById(id).get();
    }

    public Company save(Company company){
       return companyRepository.save(company);
    }

    public List<Company> findAllCompany(){
        return companyRepository.findAll();
    }

    public List<CompanyDto> findAllCompanyWithEmployees() {

        List<Employee> allEmployees = employeeClient.getAll();

        List<Company> allCompanies = companyRepository.findAll();

       List<CompanyDto>finalResponse = new ArrayList<>();
        for (int i = 0; i < allCompanies.size(); i++) {
            Company company = allCompanies.get(i);
            Long compRegNum = company.getCompRegNum();
            for (int j = 0; j < allEmployees.size(); j++) {
                Employee employee = allEmployees.get(j);
                Long companyId = employee.getCompanyId();
                if (companyId==compRegNum){
                    CompanyDto companyDto = companyMapper.companyToDto(company);
                   companyDto.addEmployee(employee);
                   finalResponse.add(companyDto);
                }
            }
        }
        return finalResponse;
    }

    public void initDbInsertCompanies(){
        Company Emerson = new Company(1L, "Emerson", "8000 Székesfehérvár Holland Fasor 13");
        Company Denso = new Company(2L, "Denso", "8000 Székesfehérvár Holland Fasor 14");
        Company Grundfos = new Company(3L, "Grundfos", "8000 Székesfehérvár Holland Fasor 15");
        companyRepository.save(Emerson);
        companyRepository.save(Denso);
        companyRepository.save(Grundfos);
    }
}
