package com.kv.backpack.service;

import com.kv.backpack.Constants;
import com.kv.backpack.domain.dto.BackpackDto;
import com.kv.backpack.domain.entity.Backpack;
import com.kv.backpack.domain.entity.common.enumeration.BackpackSize;
import com.kv.backpack.domain.entity.food.FoodItem;
import com.kv.backpack.domain.entity.things.ThingItem;
import com.kv.backpack.repository.BackpackRepository;
import com.kv.backpack.repository.FoodItemRepository;
import com.kv.backpack.repository.ThingItemRepository;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BackpackServiceTest {

    @Mock
    private BackpackRepository backpackRepository;

    @Mock
    private ThingItemRepository thingItemRepository;

    @Mock
    private FoodItemRepository foodItemRepository;

    private final Clock clock = Clock.fixed(Instant.parse("2021-07-01T10:00:00Z"), ZoneId.systemDefault());

    private BackpackService backpackService;

    @BeforeEach
    void setUp() {
        ThingItemService thingItemService = new ThingItemService(thingItemRepository);
        FoodItemService foodItemService = new FoodItemService(foodItemRepository);
        backpackService = new BackpackService(backpackRepository, thingItemService, foodItemService, clock);
    }



    @Test
    void testCreateBackpackOnSeason() {
        var km = 10;
        var itemOne = Instancio.of(ThingItem.class)
                        .set(Select.field("name"), "Tent")
                        .set(Select.field("season"), "All")
                        .create();
        var itemTwo = Instancio.of(ThingItem.class)
                        .set(Select.field("season"), "Winter")
                        .create();
        itemOne.setHeight(1.0);
        itemOne.setWidth(1.0);
        itemOne.setLength(1.0);
        itemOne.setWeight(1.0);
        itemTwo.setHeight(1.0);
        itemTwo.setWidth(1.0);
        itemTwo.setLength(1.0);
        itemTwo.setWeight(1.0);

        var foodOne = Instancio.of(FoodItem.class).create();
        var foodTwo = Instancio.of(FoodItem.class).create();
        foodOne.setHeight(1.0);
        foodOne.setWidth(1.0);
        foodOne.setLength(1.0);
        foodOne.setWeight(1.0);
        foodTwo.setHeight(1.0);
        foodTwo.setWidth(1.0);
        foodTwo.setLength(1.0);
        foodTwo.setWeight(1.0);


        Mockito.when(thingItemRepository.findAll()).thenReturn(List.of(itemOne, itemTwo));
        Mockito.when(foodItemRepository.findAll()).thenReturn(List.of(foodOne, foodTwo));

        var response = backpackService.createBackpackOnSeason(km);

        Assertions.assertEquals(2, response.getFoodItems().size());
        Assertions.assertEquals(1, response.getBackpackItems().size());
        Assertions.assertEquals("Tent", response.getBackpackItems().get(0).getName());
        Mockito.verify(backpackRepository, Mockito.times(1)).save(Mockito.any());
        Assertions.assertEquals(String.format(Constants.BACKPACK_SIZE, BackpackSize.SMALL.name(), 2.3), response.getSize());
    }

    @Test
    void testGetPreCreatedBackpack() {
        var backpacks = List.of(
                Instancio.of(Backpack.class).create(),
                Instancio.of(Backpack.class).create()
        );

        Mockito.when(backpackRepository.findAll()).thenReturn(backpacks);

        var response = backpackService.getPreCreatedBackpack();

        Assertions.assertEquals(2, response.size());
        Assertions.assertEquals(BackpackDto.class, response.get(0).getClass());
        Mockito.verify(backpackRepository, Mockito.times(1)).findAll();
    }
}
