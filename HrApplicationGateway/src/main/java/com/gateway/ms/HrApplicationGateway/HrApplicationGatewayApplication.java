package com.gateway.ms.HrApplicationGateway;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;


@SpringBootApplication
@EnableDiscoveryClient
public class HrApplicationGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrApplicationGatewayApplication.class, args);
	}


	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder routeLocatorBuilder){
		return routeLocatorBuilder.routes()
				.route(predicateSpec -> predicateSpec.path("/api/companies/**")
						.filters(f->f.circuitBreaker(config -> config.setName("companyFilter").setFallbackUri("forward:/http://localhost:8050/fallback/employeeFallBack")))
						.uri("http://localhost:8056/api/companies"))
				.route(predicateSpec -> predicateSpec.path("/api/employees/**")
						.filters(f->f.circuitBreaker(config -> config.setName("employeeFilter").setFallbackUri(URI.create("http://localhost:8050/fallback/employeeFallBack"))))
						.uri("http://localhost:8055"))
				.route(predicateSpec -> predicateSpec.path("api/holidays/**")
						.filters(f->f.circuitBreaker(config -> config.setName("holidayFilter").setFallbackUri("http://localhost:8050/fallback/holidayFallBack")))
						.uri("http://localhost:8060"))
				.build();
	}

	@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
				.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
				.timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofMillis(200)).build())
				.build());
	}

	@RequestMapping("/fallback")
	public Mono<String> fallback() {
		return Mono.just("fallback");
	}

}
