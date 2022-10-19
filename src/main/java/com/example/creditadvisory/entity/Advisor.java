package com.example.creditadvisory.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "advisors")
public class Advisor extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToMany(mappedBy = "advisor")
    @Setter(AccessLevel.PRIVATE)
    private List<Application> applicationList;

    public enum Role {
        ASSOCIATE, PARTNER, SENIOR
    }

    public boolean hasAssignedApplication() {
        return applicationList.stream()
                .anyMatch(application -> application.getStatus() == Application.Status.ASSIGNED);
    }

    public void addApplication(Application application) {
        application.setAdvisor(this);
        applicationList.add(application);
    }
}
