package com.example.api_nosql.api.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/health")
public interface HealthApi {

    @GetMapping("/check")
    String healthCheck();
}
