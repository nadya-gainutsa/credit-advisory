package com.example.creditadvisory.repository;

import com.example.creditadvisory.entity.Advisor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvisorRepository extends JpaRepository<Advisor, Long> {
}
