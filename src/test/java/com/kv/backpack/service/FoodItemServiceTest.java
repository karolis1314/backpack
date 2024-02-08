package com.kv.backpack.service;

import com.kv.backpack.domain.dto.FoodItemDto;
import com.kv.backpack.domain.entity.food.FoodItem;
import com.kv.backpack.repository.FoodItemRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class FoodItemServiceTest {

    @Mock
    private FoodItemRepository foodItemRepository;

    @InjectMocks
    private FoodItemService foodItemService;


    @Test
    void testGetAllFoodItems() {
        var list = List.of(
                Instancio.of(FoodItem.class).create(),
                Instancio.of(FoodItem.class).create()
        );

        Mockito.when(foodItemRepository.findAll()).thenReturn(list);

        var response = foodItemService.getAllFoodItems();

        Mockito.verify(foodItemRepository, Mockito.times(1)).findAll();
        Assertions.assertNotNull(response);
        Assertions.assertEquals(2, response.size());
        Assertions.assertEquals(FoodItem.class, response.get(0).getClass());
    }

    @Test
    void testGetAllFoodItemsDto() {
        var list = List.of(
                Instancio.of(FoodItem.class).create(),
                Instancio.of(FoodItem.class).create()
        );

        Mockito.when(foodItemRepository.findAll()).thenReturn(list);

        var response = foodItemService.getAllFoodItemsDto();

        Mockito.verify(foodItemRepository, Mockito.times(1)).findAll();
        Assertions.assertNotNull(response);
        Assertions.assertEquals(2, response.size());
        Assertions.assertEquals(FoodItemDto.class, response.get(0).getClass());
    }
}
