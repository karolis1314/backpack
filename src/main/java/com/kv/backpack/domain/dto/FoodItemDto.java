package com.kv.backpack.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodItemDto {

    private Long id;
    private String name;
    private Long calories;
    private String flavour;
    private Boolean liquid;
    private String weightInGrams;
}
