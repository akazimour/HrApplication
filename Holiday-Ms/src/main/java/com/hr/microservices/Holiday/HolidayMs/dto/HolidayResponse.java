package com.hr.microservices.Holiday.HolidayMs.dto;

import com.hr.microservices.Holiday.HolidayMs.model.ApprovalStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class HolidayResponse {

    @GeneratedValue
    private Long id;
    private LocalDateTime requestDate;
    private LocalDate holidayStart;
    private LocalDate holidayEnd;
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;
    private Long requesterId;
    private Long approverId;
    private Long companyId;

    public HolidayResponse() {
    }

    public HolidayResponse(Long id, LocalDateTime requestDate, LocalDate holidayStart, LocalDate holidayEnd, ApprovalStatus approvalStatus) {
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
    public Long getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }

    public Long getApproverId() {
        return approverId;
    }

    public void setApproverId(Long approverId) {
        this.approverId = approverId;
    }
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

}
