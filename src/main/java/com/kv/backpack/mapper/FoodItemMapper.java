package com.kv.backpack.mapper;

import com.kv.backpack.Constants;
import com.kv.backpack.domain.dto.FoodItemDto;
import com.kv.backpack.domain.entity.food.FoodItem;

public class FoodItemMapper {

    public static FoodItemDto toFoodItemDto(FoodItem foodItem) {
        return FoodItemDto.builder()
                .id(foodItem.getId())
                .name(foodItem.getName())
                .calories(foodItem.getCalories())
                .flavour(foodItem.getFlavour().getValue())
                .liquid(foodItem.getLiquid())
                .weightInGrams(String.format(Constants.WEIGHT_IN_GRAMS, foodItem.getWeight() * 1000))
                .build();
    }
}
