package com.kv.backpack.controller;

import com.kv.backpack.domain.dto.FoodItemDto;
import com.kv.backpack.service.FoodItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Food", description = "Food API")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/food")
public class FoodItemController {

    private final FoodItemService foodItemService;

    @Operation(summary = "Get all food items")
    @GetMapping
    @ResponseBody
    public List<FoodItemDto> getFoodItems() {
        return foodItemService.getAllFoodItemsDto();
    }
}
