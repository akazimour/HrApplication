package com.hr.microservices.Holiday.HolidayMs.controller;

import com.hr.microservices.Holiday.HolidayMs.dto.HolidayRequestDto;
import com.hr.microservices.Holiday.HolidayMs.dto.HolidayResponse;
import com.hr.microservices.Holiday.HolidayMs.mapper.HolidayMapper;
import com.hr.microservices.Holiday.HolidayMs.model.HolidayRequest;
import com.hr.microservices.Holiday.HolidayMs.service.HolidayService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/holidays")
public class HoRequestController {
@Autowired
HolidayService holidayService;

@Autowired
HolidayMapper holidayMapper;

    static final String HOLIDAY_FILTER = "holidayFilter";

    @PostMapping("/employee/{EmpId}")
    @CircuitBreaker(name = HOLIDAY_FILTER,fallbackMethod = "fallback")
    public HolidayRequestDto addNewHolidayRequestToEmployee(@RequestBody HolidayRequest holidayRequest, @PathVariable Long EmpId){
        return holidayService.addNewHolidayRequest(holidayRequest, EmpId);

    }

    @GetMapping("/all")
    public List<HolidayResponse> getAllHolidays(){
       return holidayMapper.hoRequestsToResponses(holidayService.findAllHolidays());
    }

    @PutMapping("/{holidayId}/approve/{approverId}")
    public HolidayRequest approveHolidayRequest(@PathVariable Long holidayId, @PathVariable Long approverId) {
        return holidayService.approveRequestStatus(holidayId, approverId);
    }

    @PutMapping("/{holidayId}/reject/{rejecterId}")
    public HolidayRequest rejectHolidayRequest(@PathVariable Long holidayId, @PathVariable Long rejecterId) {
        return holidayService.rejectRequestStatus(holidayId, rejecterId);
    }
    @RequestMapping("/fallback")
    public HolidayRequestDto fallback(Exception e){
        throw new ResponseStatusException(HttpStatus.GATEWAY_TIMEOUT,"Company or Employee services are taking too long to respond or some of them are down! Please try again later...");
    }
    @DeleteMapping("/{holidayId}")
    public ResponseEntity<String>removeHoliday(@PathVariable Long holidayId){
        return holidayService.deleteHoliday(holidayId);
    }
}


