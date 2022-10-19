package com.example.creditadvisory.entity;

import lombok.Data;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "applicants")
public class Applicant extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String ssn;

    @Embedded
    private Address address;

    @ElementCollection
    @CollectionTable(name = "phones", joinColumns = @JoinColumn(name = "applicant_id"))
    private List<Phone> phoneList;

    public void addPhone(Phone phone) {
        this.phoneList.add(phone);
    }
}
