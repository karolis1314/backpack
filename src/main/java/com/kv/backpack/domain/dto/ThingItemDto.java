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
public class ThingItemDto {

        private Long id;
        private String name;
        private String color;
        private Boolean fitsToSmallSection;
        private String material;
        private List<String> attributes;
        private String weightInGrams;
}
