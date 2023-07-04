package com.example.ordermanagement.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@Data
public class UserDTO {

    private Long id;
    private String username;
    private Set<String> roles;

}
