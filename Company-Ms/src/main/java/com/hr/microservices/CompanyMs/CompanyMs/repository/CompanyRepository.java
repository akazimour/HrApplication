package com.hr.microservices.CompanyMs.CompanyMs.repository;

import com.hr.microservices.CompanyMs.CompanyMs.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {

}
