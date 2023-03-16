package com.hr.microservices.CompanyMs.CompanyMs;

import com.hr.microservices.CompanyMs.CompanyMs.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients

public class CompanyMsApplication implements CommandLineRunner {
	@Autowired
    CompanyService companyService;
	public static void main(String[] args) {
		SpringApplication.run(CompanyMsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		companyService.initDbInsertCompanies();
	}
}
