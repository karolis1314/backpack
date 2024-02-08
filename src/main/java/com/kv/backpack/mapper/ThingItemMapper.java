package com.kv.backpack.mapper;

import com.kv.backpack.Constants;
import com.kv.backpack.domain.dto.ThingItemDto;
import com.kv.backpack.domain.entity.things.ThingItem;

public class ThingItemMapper {

    public static ThingItemDto toThingItemDto(ThingItem thingItem) {
        return ThingItemDto.builder()
                .id(thingItem.getId())
                .name(thingItem.getName())
                .color(thingItem.getColor())
                .fitsToSmallSection(thingItem.getFitsToSmallSection())
                .material(thingItem.getMaterial().getValue())
                .weightInGrams(String.format(Constants.WEIGHT_IN_GRAMS, thingItem.getWeight() * 1000))
                .attributes(thingItem.getAttributes())
                .build();
    }
}
