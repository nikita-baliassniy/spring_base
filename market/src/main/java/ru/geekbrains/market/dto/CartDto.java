package ru.geekbrains.market.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartDto {
    private Long id;
    private String title;
    private Double cost;
    private Integer quantity;

}

