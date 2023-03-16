package com.hr.microservices.Holiday.HolidayMs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class HolidayMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HolidayMsApplication.class, args);
	}

}
