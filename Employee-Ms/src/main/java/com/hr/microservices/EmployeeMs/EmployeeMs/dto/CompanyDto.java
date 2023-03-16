package com.hr.microservices.EmployeeMs.EmployeeMs.dto;

public class CompanyDto {


    private Long CompRegNum;
    private String CompanyName;
    private String CompanyAddress;

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
}
