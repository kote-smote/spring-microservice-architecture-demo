package com.martin.dummyapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class DummyAppApplication {

    private static long COUNTER = 0;

    @GetMapping("/hello")
    public String home() {
        return "Dummy App says hello " + COUNTER++ + " times";
    }

	public static void main(String[] args) {
		SpringApplication.run(DummyAppApplication.class, args);
	}
}
