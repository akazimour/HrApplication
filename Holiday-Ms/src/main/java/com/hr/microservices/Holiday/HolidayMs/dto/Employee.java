package com.hr.microservices.Holiday.HolidayMs.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@JsonPropertyOrder({"id", "name", "salary", "position", "startDate", "companyId"})
public class Employee {


    private Long id;
    private String name;
    private int salary;

    private String position;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startDate;

    private CompanyDto company;

    public Employee() {
    }

    public Employee(Long id, String name, int salary, String position, LocalDateTime startDate) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.position = position;
        this.startDate = startDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public CompanyDto getCompany() {
        return company;
    }
    public void setCompany(CompanyDto company) {
        this.company = company;
    }
}
