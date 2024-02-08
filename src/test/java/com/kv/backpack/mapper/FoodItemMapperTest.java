package com.kv.backpack.mapper;

import com.kv.backpack.domain.entity.common.enumeration.Flavour;
import com.kv.backpack.domain.entity.food.FoodItem;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FoodItemMapperTest {

    @Test
    void testToFoodItemDto() {
        var foodItem = Instancio.of(FoodItem.class)
                .set(Select.field("calories"), 52L)
                .set(Select.field("flavour"), Flavour.SWEET)
                .create();

        var foodItemDto = FoodItemMapper.toFoodItemDto(foodItem);

        Assertions.assertNotNull(foodItemDto);
        Assertions.assertEquals(52, foodItemDto.getCalories());
        Assertions.assertEquals(Flavour.SWEET.getValue(), foodItemDto.getFlavour());
    }
}
