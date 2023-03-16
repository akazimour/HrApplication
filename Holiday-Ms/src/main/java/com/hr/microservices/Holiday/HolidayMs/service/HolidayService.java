package com.hr.microservices.Holiday.HolidayMs.service;

import com.hr.microservices.Holiday.HolidayMs.dto.Employee;
import com.hr.microservices.Holiday.HolidayMs.dto.HolidayRequestDto;
import com.hr.microservices.Holiday.HolidayMs.feignClient.EmployeeClient;
import com.hr.microservices.Holiday.HolidayMs.mapper.HolidayMapper;
import com.hr.microservices.Holiday.HolidayMs.model.ApprovalStatus;
import com.hr.microservices.Holiday.HolidayMs.model.HolidayRequest;
import com.hr.microservices.Holiday.HolidayMs.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class HolidayService {

    @Autowired
    EmployeeClient employeeClient;
    @Autowired
    HolidayRepository holidayRepository;

    @Autowired
    HolidayMapper holidayMapper;

    public HolidayRequestDto addNewHolidayRequest(HolidayRequest holidayRequest, Long employeeId) {

        Employee employeeById = employeeClient.getEmployeeById(employeeId);
        if(!employeeId.equals(null)){
            //db part
                    holidayRequest.setRequesterId(employeeById.getId());
                    holidayRequest.setApprovalStatus(ApprovalStatus.WAITING_FOR_APPROVAL);
                    holidayRequest.setCompanyId(employeeById.getCompany().getCompRegNum());
                    holidayRepository.save(holidayRequest);
            HolidayRequestDto holidayRequestDto = holidayMapper.holidayRequestToDto(holidayRequest);
            holidayRequestDto.setRequester(employeeById);
            return holidayRequestDto;
        }else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " HolidayRequest does not exist with id: " + employeeId);
    }

    public List<HolidayRequest> findAllHolidays(){
       return holidayRepository.findAll();
    }

    public HolidayRequest approveRequestStatus(Long id, Long employeeId) {

        Optional<HolidayRequest> byId = holidayRepository.findById(id);
        Employee employeeById = employeeClient.getEmployeeById(employeeId);
        if (!byId.isPresent() || employeeById == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " HolidayRequest does not exist with id: " + id + " or Manager can not be found with id: "+employeeId);
}
        HolidayRequest holidayRequest = holidayRepository.findById(id).get();

        if (holidayRequest.getApprovalStatus().equals(ApprovalStatus.WAITING_FOR_APPROVAL) && employeeById.getPosition().equals("Manager")) {
            holidayRequest.setApproverId(employeeById.getId());
            holidayRequest.setApprovalStatus(ApprovalStatus.APPROVED);
            return holidayRepository.save(holidayRequest);
        } else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " HolidayRequest does not exist with id: " + id + " or you are not a Manager");
    }
    public HolidayRequest rejectRequestStatus(Long id, Long employeeId) {
        Optional<HolidayRequest> byId = holidayRepository.findById(id);
        Employee employeeById = employeeClient.getEmployeeById(employeeId);
        if (!byId.isPresent() || employeeById.equals(null)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " HolidayRequest does not exist with id: " + id + " or Manager can not be found with id: "+employeeId);
        }
        HolidayRequest holidayRequest = holidayRepository.findById(id).get();
        if (holidayRequest.getApprovalStatus().equals(ApprovalStatus.WAITING_FOR_APPROVAL) && employeeById.getPosition().equals("Manager")) {
            holidayRequest.setApproverId(employeeById.getId());
            holidayRequest.setApprovalStatus(ApprovalStatus.NOT_APPROVED);
            return holidayRepository.save(holidayRequest);
        } else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " HolidayRequest does not exist with id: " + id + " or you are not a Manager");
    }

    public ResponseEntity<String> deleteHoliday(Long holidayId){
        holidayRepository.deleteById(holidayId);
        return ResponseEntity.ok("Holiday request has been successfully deleted with id: "+holidayId);
    }

}