package com.example.amazon.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor @Data
public class Merchant {

    @NotEmpty(message = "ID has to be not empty")
    @Size(min=3,message="ID has to be at least 3 char long")
    private String id;

    @NotEmpty(message = "Name has to be not empty")
    @Size(min=3,message="Name has to be at least 3 length long")
    private String name;

}
