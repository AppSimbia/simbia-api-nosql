package com.example.api_nosql.api.health;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeallthController implements HealthApi{
    @Override
    public String healthCheck() {
        return "I'm Still Standing!";
    }
}
