package com.example.creditadvisory.controller;

import com.example.creditadvisory.entity.Application;
import com.example.creditadvisory.service.AdvisorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class AdvisorController {
    private AdvisorService advisorService;

    @PostMapping("/assign/{id}")
    public Application assignApplication(@PathVariable("id") Long advisorId) {
        return advisorService.assignToApplication(advisorId);
    }
}
