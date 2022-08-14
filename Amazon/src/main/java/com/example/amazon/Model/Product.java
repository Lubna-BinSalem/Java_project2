package com.example.amazon.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.internal.constraintvalidators.bv.time.past.PastValidatorForReadableInstant;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor @Data
public class Product {

    @NotEmpty(message = "ID has to be not empty")
    @Size(min=3,message="ID has to be at least 3 char long")
    private String id;

    @NotEmpty(message = "Name has to be not empty")
    @Size(min=3,message="Name has to be at least 3 length long")
    private String name;

    @NotEmpty(message = "Price has to be not empty")
    @Pattern(regexp = "[\\s]*[1-9]*[0-9]",message="Price must be a number")
    private String price;

    @NotEmpty(message = "The category ID has to be not empty")
    @Size(min=3,message="The category ID has to be at least 3 length long")
    private String categoryID;

}
