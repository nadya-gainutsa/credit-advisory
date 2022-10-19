package com.example.creditadvisory.repository;

import com.example.creditadvisory.entity.Application;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ApplicationRepository implements CustomApplicationRepository {
    private final EntityManager entityManager;

    @Override
    public Optional<Application> findByNewStatusAndAmountRange(BigDecimal min, BigDecimal max) {
        return entityManager.createQuery("select a from Application a " +
                        "where a.status = 'NEW' " +
                        "and a.amount >= ?1 and a.amount <= ?2 " +
                        "order by a.createdAt",
                    Application.class)
                .setParameter(1, min)
                .setParameter(2, max)
                .setMaxResults(1)
                .getResultStream()
                .findAny();
    }

    @Override
    public Optional<Application> findByNewStatusAndAmountMin(BigDecimal min) {
        return entityManager.createQuery("select a from Application a " +
                                "where a.status = 'NEW' " +
                                "and a.amount > ?1 " +
                                "order by a.createdAt",
                        Application.class)
                .setParameter(1, min)
                .setMaxResults(1)
                .getResultStream()
                .findAny();
    }

    @Override
    public Optional<Application> findByNewStatusAndAmountMax(BigDecimal max) {
        return entityManager.createQuery("select a from Application a " +
                                "where a.status = 'NEW' " +
                                "and a.amount < ?1 " +
                                "order by a.createdAt",
                        Application.class)
                .setParameter(1, max)
                .setMaxResults(1)
                .getResultStream()
                .findAny();
    }
}
