package com.example.creditadvisory.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(of = "cognitoUserName")
public class User {
    private String email;
    private String cognitoUserName;
}
