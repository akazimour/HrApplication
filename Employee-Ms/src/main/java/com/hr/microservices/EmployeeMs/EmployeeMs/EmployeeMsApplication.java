package com.hr.microservices.EmployeeMs.EmployeeMs;

import com.hr.microservices.EmployeeMs.EmployeeMs.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class EmployeeMsApplication implements CommandLineRunner{
	@Autowired
EmployeeService employeeService;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeMsApplication.class, args);
	}

		@Override
		public void run(String... args) throws Exception {
		employeeService.initDbAddEmployeesToCompanies();

		}


}

