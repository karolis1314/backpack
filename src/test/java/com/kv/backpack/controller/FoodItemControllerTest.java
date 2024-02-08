package com.kv.backpack.controller;

import com.kv.backpack.domain.dto.FoodItemDto;
import com.kv.backpack.service.FoodItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class FoodItemControllerTest {

    @Mock
    private FoodItemService foodItemService;

    @InjectMocks
    private FoodItemController foodItemController;


    @Test
    void testGetFoodItems() {
        Mockito.when(foodItemService.getAllFoodItemsDto()).thenReturn(List.of(new FoodItemDto()));

        var response = foodItemController.getFoodItems();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals(FoodItemDto.class, response.get(0).getClass());
    }
}
