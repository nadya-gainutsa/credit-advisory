package com.example.creditadvisory.service;


import com.example.creditadvisory.entity.Advisor;
import com.example.creditadvisory.entity.Application;
import com.example.creditadvisory.repository.AdvisorRepository;
import com.example.creditadvisory.repository.CustomApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdvisorService {
    private AdvisorRepository advisorRepository;
    private CustomApplicationRepository applicationRepository;

    @Transactional
    public Application assignToApplication(Long advisorId) {
        Advisor advisor = advisorRepository.findById(advisorId)
                .orElseThrow(() -> new IllegalArgumentException("Advisor with id " + advisorId + " does not exist"));
        if (advisor.hasAssignedApplication()) {
            throw new IllegalStateException("Advisor already has an assigned application");
        }

        Application application = getApplication(advisor);
        application.assign();
        advisor.addApplication(application);

        return application;

    }

    private Application getApplication(Advisor advisor) {
        Optional<Application> application = switch (advisor.getRole()) {
            case ASSOCIATE -> applicationRepository.findByNewStatusAndAmountMax(BigDecimal.valueOf(10_000));
            case PARTNER -> applicationRepository.findByNewStatusAndAmountRange(BigDecimal.valueOf(10_000), BigDecimal.valueOf(50_000));
            case SENIOR -> applicationRepository.findByNewStatusAndAmountMin(BigDecimal.valueOf(50_000));
        };

        return application
                .orElseThrow(() -> new IllegalArgumentException("Not found application for advisor with id " + advisor.getId()));
    }
}
