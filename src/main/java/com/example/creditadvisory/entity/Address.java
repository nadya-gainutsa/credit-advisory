package com.example.creditadvisory.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class Address {
    private String city;
    private String street;
    @Column(name = "str_number")
    private String number;
    private String zip;
    private String apt;
}
