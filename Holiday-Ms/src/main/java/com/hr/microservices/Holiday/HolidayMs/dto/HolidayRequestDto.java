package com.hr.microservices.Holiday.HolidayMs.dto;

import com.hr.microservices.Holiday.HolidayMs.model.ApprovalStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class HolidayRequestDto {

    @GeneratedValue
    private Long id;
    private LocalDateTime requestDate;
    private LocalDate holidayStart;
    private LocalDate holidayEnd;
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;
    private Employee requester;
    private Employee approver;
    private Long companyId;

    public HolidayRequestDto() {
    }

    public HolidayRequestDto(Long id, LocalDateTime requestDate, LocalDate holidayStart, LocalDate holidayEnd, ApprovalStatus approvalStatus) {
        this.id = id;
        this.requestDate = requestDate;
        this.holidayStart = holidayStart;
        this.holidayEnd = holidayEnd;
        this.approvalStatus = approvalStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDate getHolidayStart() {
        return holidayStart;
    }

    public void setHolidayStart(LocalDate holidayStart) {
        this.holidayStart = holidayStart;
    }

    public LocalDate getHolidayEnd() {
        return holidayEnd;
    }

    public void setHolidayEnd(LocalDate holidayEnd) {
        this.holidayEnd = holidayEnd;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Employee getRequester() {
        return requester;
    }

    public void setRequester(Employee requester) {
        this.requester = requester;
    }

    public Employee getApprover() {
        return approver;
    }

    public void setApprover(Employee approver) {
        this.approver = approver;
    }

    public Long getCompanyId() {
        return companyId;
    }
}
