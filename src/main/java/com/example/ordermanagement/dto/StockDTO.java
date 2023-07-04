package com.example.ordermanagement.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
public class StockDTO {

    private Integer id;
    private Integer productId;
    private Integer quantity;
    private LocalDateTime updateAt;
}
