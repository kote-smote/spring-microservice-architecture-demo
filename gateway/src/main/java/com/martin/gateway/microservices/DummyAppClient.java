package com.martin.gateway.microservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("dummy-app")
public interface DummyAppClient {

    @GetMapping("/hello")
    String hello();

}
