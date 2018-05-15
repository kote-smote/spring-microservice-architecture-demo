package com.martin.gateway;

import com.martin.gateway.microservices.DummyAppClient;
import com.martin.gateway.microservices.DummyAppWithDbClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GateControllerFeign {
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private DummyAppClient dummyAppClient;
    @Autowired
    private DummyAppWithDbClient dummyAppWithDbClient;

    @GetMapping("/services")
    public List<String> services() {
        return this.discoveryClient.getServices();
    }

    @GetMapping("/services/{serviceId}/instances")
    public List<ServiceInstance> serviceInstances(@PathVariable String serviceId) {
        return this.discoveryClient.getInstances(serviceId);
    }

    @GetMapping("/dummy-app/hello")
    public String dummyAppHello() {
        return this.dummyAppClient.hello();
    }

    @GetMapping("/dummy-app-with-db/hello")
    public String dummyAppWithDbHello() {
        return this.dummyAppWithDbClient.hello();
    }

    @GetMapping("/hello-from-both")
    public String helloAggregated() {
        String hello1 = this.dummyAppClient.hello();
        String hello2 = this.dummyAppWithDbClient.hello();
        return String.format("Answer 1: %s | Answer 2: %s", hello1, hello2);
    }
}
