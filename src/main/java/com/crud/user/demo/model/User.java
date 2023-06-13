package com.crud.user.demo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private String firstName;
    private String lastName;
    private String contactNumber;
}
