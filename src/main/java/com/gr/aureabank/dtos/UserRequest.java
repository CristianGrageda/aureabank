package com.gr.aureabank.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String dni;
}
