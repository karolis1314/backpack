package com.kv.backpack.service;

import com.kv.backpack.domain.dto.FoodItemDto;
import com.kv.backpack.domain.entity.food.FoodItem;
import com.kv.backpack.mapper.FoodItemMapper;
import com.kv.backpack.repository.FoodItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;


    public List<FoodItem> getAllFoodItems() {
        return foodItemRepository.findAll();
    }

    public List<FoodItemDto> getAllFoodItemsDto() {
        return foodItemRepository.findAll().stream()
                .map(FoodItemMapper::toFoodItemDto)
                .toList();
    }
}
