package com.example.creditadvisory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.type.LocalDateType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "applications")
public class Application {
    @Id
    @GeneratedValue
    private Long id;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime assignedAt;
    private LocalDateTime resolvedAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "advisor_id")
    private Advisor advisor;

    public enum Status {
        NEW, ASSIGNED, ON_HOLD, APPROVED, CANCELED, DECLINED
    }

    public void assign() {
        this.status = Status.ASSIGNED;
        this.assignedAt = LocalDateTime.now();
    }
}
