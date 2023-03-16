package com.hr.microservices.CompanyMs.CompanyMs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Company {

    @Id
    @GeneratedValue
    private Long CompRegNum;
    private String CompanyName;
    private String CompanyAddress;

    public Company() {
    }

    public Company(Long compRegNum, String companyName, String companyAddress) {
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
}
