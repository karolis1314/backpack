package com.kv.backpack.mapper;

import com.kv.backpack.Constants;
import com.kv.backpack.domain.dto.BackpackDto;
import com.kv.backpack.domain.entity.Backpack;
import com.kv.backpack.utils.BackpackUtil;

public class BackpackMapper {

    public static BackpackDto toBackPackDto(Backpack backpack) {
        return BackpackDto.builder()
                .id(backpack.getId())
                .size(String.format(Constants.BACKPACK_SIZE, backpack.getSize().name(), backpack.getWeight()))
                .volume(String.format(Constants.BACKPACK_VOLUME, Math.round(BackpackUtil.calculateVolume(backpack.getHeight(), backpack.getWidth(), backpack.getLength()) / 1000)))
                .backpackItems(backpack.getBackpackItems()
                        .stream()
                        .map(ThingItemMapper::toThingItemDto)
                        .toList())
                .foodItems(backpack.getFoodItems()
                        .stream()
                        .map(FoodItemMapper::toFoodItemDto)
                        .toList())
                .build();
    }
}
