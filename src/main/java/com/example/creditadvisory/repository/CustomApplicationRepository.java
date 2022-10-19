package com.example.creditadvisory.repository;

import com.example.creditadvisory.entity.Application;

import java.math.BigDecimal;
import java.util.Optional;

public interface CustomApplicationRepository {
    Optional<Application> findByNewStatusAndAmountRange(BigDecimal min, BigDecimal max);
    Optional<Application> findByNewStatusAndAmountMin(BigDecimal min);
    Optional<Application> findByNewStatusAndAmountMax(BigDecimal max);
}
