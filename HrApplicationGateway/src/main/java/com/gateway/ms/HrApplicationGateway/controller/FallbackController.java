package com.gateway.ms.HrApplicationGateway.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fallback")

public class FallbackController {

    @GetMapping("/employeeFallBack")
    public Mono<String> employeeMsFallBack(){
        return Mono.just("Employee service is taking too long to respond or it is down. Please try again later...");
    }

    @GetMapping("/companyFallBack")
    public Mono<String> companyMsFallBack(){
        return Mono.just("Company service is taking too long to respond or it is down. Please try again later...");
    }

    @GetMapping("/holidayFallBack")
    public Mono<String> holidayMsFallBack(){
        return Mono.just("Holiday service is taking too long to respond or it is down. Please try again later...");
    }
}
