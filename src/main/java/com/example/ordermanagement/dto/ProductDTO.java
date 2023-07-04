package com.example.ordermanagement.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;


@Getter
@Setter
@Data
public class ProductDTO  extends RepresentationModel<ProductDTO> {
    private Integer id;
    private String slug;
    private String name;
    private String reference;
    @Column(precision = 10, scale = 2)
    private BigDecimal price;
    @Column(precision = 10, scale = 2)
    private BigDecimal vat;
    private Boolean stockable;
}
