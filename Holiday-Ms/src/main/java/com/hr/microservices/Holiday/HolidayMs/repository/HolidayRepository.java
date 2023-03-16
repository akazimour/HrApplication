package com.hr.microservices.Holiday.HolidayMs.repository;

import com.hr.microservices.Holiday.HolidayMs.model.HolidayRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepository extends JpaRepository<HolidayRequest,Long> {
}
