package com.example.amazon.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor @Data
public class User {

    @NotEmpty(message = "ID has to be not empty")
    @Size(min=3,message="ID has to be at least 3 length long")
    private String id;

    @NotEmpty(message = "Username has to be not empty")
    @Size(min=5,max=5,message="Username has to be at least 5 length long")
    private String username;

    @NotEmpty(message = "Password has to be not empty")
    @Size(min=6,message="Password has to be at least 6 length long")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$", message = "Password must contain letters and digits")
    private String password;

    @NotEmpty(message = "Email has to be not empty")
    @Email
    private String email;

    @NotEmpty(message = "Role has to be not empty")
    @Pattern(regexp = "Admin|Customer", message = "Role must be either Admin or Customer")
    private String role;

    @NotEmpty(message = "Balance has to be not empty")
    @Pattern(regexp = "['']*[1-9]*[0-9]", message = "Balance must be positive number")
    private String balance;

}
