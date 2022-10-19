package com.example.creditadvisory.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@EqualsAndHashCode(of = "number")
public class Phone {
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "phone_type")
    private PhoneType type;

    public enum PhoneType {
        WORK, HOME, MOBILE
    }
}
