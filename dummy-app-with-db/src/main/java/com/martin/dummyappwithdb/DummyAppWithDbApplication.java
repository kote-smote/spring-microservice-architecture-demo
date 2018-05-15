package com.martin.dummyappwithdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class DummyAppWithDbApplication {

	private static long COUNTER = 0;

	@GetMapping("/hello")
	public String home() {
		return "Dummy App With DB says hello " + COUNTER++ + " times";
	}

	public static void main(String[] args) {
		SpringApplication.run(DummyAppWithDbApplication.class, args);
	}
}
