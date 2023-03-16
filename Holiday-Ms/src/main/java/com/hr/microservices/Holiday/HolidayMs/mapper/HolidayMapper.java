package com.hr.microservices.Holiday.HolidayMs.mapper;

import com.hr.microservices.Holiday.HolidayMs.dto.HolidayRequestDto;
import com.hr.microservices.Holiday.HolidayMs.dto.HolidayResponse;
import com.hr.microservices.Holiday.HolidayMs.model.HolidayRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HolidayMapper {

    HolidayRequestDto holidayRequestToDto(HolidayRequest holidayRequest);

    List<HolidayRequestDto> hoRequestsToDtos(List<HolidayRequest> holidayRequests);

    List<HolidayResponse> hoRequestsToResponses(List<HolidayRequest> holidayRequests);
    HolidayRequest holidayRequestDtoToHolidayRequest(HolidayRequestDto holidayRequestDto);

}
