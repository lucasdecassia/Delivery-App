package com.delivery.delivery_tracking.api.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemInput {

    @NotBlank
    @Valid
    private String name;

    @NotNull
    @Min(1)
    private Integer quantity;

}
