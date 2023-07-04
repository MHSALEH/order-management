package com.example.ordermanagement.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO extends RepresentationModel<CustomerDTO> {

    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate bornAt;



}
