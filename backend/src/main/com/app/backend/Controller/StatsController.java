package com.app.backend.controller;

import com.app.backend.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.spring.framework.web.bind.annotation.GetMapping;
import org.spring.framework.web.bind.annotation.RequestMapping;
import org.spring.framework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/stats")
 
 public class StatsController {

    @Autowired
    private StatsService statsService;

    @GetMapping("")
    public Map<String, Long> getStats() {
        return statsService.getStats();
    }
 }