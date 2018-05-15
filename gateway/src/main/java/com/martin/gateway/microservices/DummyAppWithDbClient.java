package com.martin.gateway.microservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("dummy-app-with-db")
public interface DummyAppWithDbClient {

    @GetMapping("/hello")
    String hello();
}
