package com.example.ordermanagement.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
public class OrderDTO extends RepresentationModel<OrderDTO> {

    private Integer id;
    private Integer customerId;
    private LocalDateTime orderAt;
}
