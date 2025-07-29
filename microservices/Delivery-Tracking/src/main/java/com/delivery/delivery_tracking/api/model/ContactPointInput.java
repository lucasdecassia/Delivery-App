package com.delivery.delivery_tracking.api.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactPointInput {

    @NotBlank
    private String zipCode;

    @NotBlank
    private String street;

    @NotBlank
    private String number;

    @NotBlank
    private String complement;

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

}
