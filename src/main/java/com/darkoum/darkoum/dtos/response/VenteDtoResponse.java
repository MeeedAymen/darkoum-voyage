package com.darkoum.darkoum.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VenteDtoResponse {

    private Long id;
    private Integer quantity;
    private Double totalPrice;
    private String description;
}
