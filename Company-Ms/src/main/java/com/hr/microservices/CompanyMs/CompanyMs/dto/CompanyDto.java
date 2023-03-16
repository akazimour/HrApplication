package com.hr.microservices.CompanyMs.CompanyMs.dto;

import java.util.ArrayList;
import java.util.List;

public class CompanyDto {


    private Long CompRegNum;
    private String CompanyName;
    private String CompanyAddress;
    private List<Employee> CompanyEmployees;

    public CompanyDto() {
    }

    public CompanyDto(Long compRegNum, String companyName, String companyAddress) {
        CompRegNum = compRegNum;
        CompanyName = companyName;
        CompanyAddress = companyAddress;
    }

    public Long getCompRegNum() {
        return CompRegNum;
    }

    public void setCompRegNum(Long compRegNum) {
        CompRegNum = compRegNum;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getCompanyAddress() {
        return CompanyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        CompanyAddress = companyAddress;
    }

    public List<Employee> getCompanyEmployees() {
        return CompanyEmployees;
    }

    public void setCompanyEmployees(List<Employee> companyEmployees) {
        CompanyEmployees = companyEmployees;
    }
    public void addEmployee(Employee employee) {
        if (this.CompanyEmployees == null)
            this.CompanyEmployees = new ArrayList<>();
        this.CompanyEmployees.add(employee);
    }
}
