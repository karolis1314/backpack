package com.kv.backpack.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BackpackDto {

        private Long id;
        private String size;
        private String volume;
        private List<FoodItemDto> foodItems;
        private List<ThingItemDto> backpackItems;
}
