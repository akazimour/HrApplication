package com.hr.microservices.CompanyMs.CompanyMs.mapper;

import com.hr.microservices.CompanyMs.CompanyMs.dto.CompanyDto;
import com.hr.microservices.CompanyMs.CompanyMs.model.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {


    CompanyDto companyToDto(Company company);

    List<CompanyDto> companiesToDtos(List<Company> companies);

    Company dtoToCompany(CompanyDto companyDto);

}
