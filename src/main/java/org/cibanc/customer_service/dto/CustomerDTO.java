package org.cibanc.customer_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {
    private Long id;
    private String nom;
    private String email;
}
