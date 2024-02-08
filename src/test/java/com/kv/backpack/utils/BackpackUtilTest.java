package com.kv.backpack.utils;

import com.kv.backpack.Constants;
import com.kv.backpack.domain.entity.common.enumeration.BackpackSize;
import com.kv.backpack.domain.entity.common.enumeration.Distance;
import com.kv.backpack.domain.entity.things.ThingItem;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

public class BackpackUtilTest {

    private final Clock CLOCK = Clock.fixed(Instant.parse("2021-07-01T10:00:00Z"), ZoneId.systemDefault());

    @Test
    void testDetermineSizeOnSeason() {
        var response = BackpackUtil.determineSizeOnSeason(CLOCK);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(BackpackSize.SMALL, response);
    }

    @Test
    void testGetCurrentSeasonName() {
        var response = BackpackUtil.getCurrentSeasonName(CLOCK);

        Assertions.assertNotNull(response);
        Assertions.assertEquals("Summer", response);
    }

    @Test
    void testGenerateItemsBasedOnSize() {
        var thingItem = Instancio.of(ThingItem.class)
                .set(Select.field("id"), 1L)
                .set(Select.field("name"), "Desktop-Pc")
                .create();
        thingItem.setWeight(10.0);
        thingItem.setHeight(60.0);
        thingItem.setWidth(30.0);
        thingItem.setLength(15.0);

        var foodItem = Instancio.of(ThingItem.class)
                .set(Select.field("id"), 1L)
                .set(Select.field("name"), "Apple")
                .create();
        foodItem.setWeight(0.1);
        foodItem.setHeight(0.05);
        foodItem.setWidth(0.05);
        foodItem.setLength(0.05);

        var thingItemForSmallMaxVolume = Instancio.of(ThingItem.class).create();
        thingItemForSmallMaxVolume.setWeight(2.0);
        thingItemForSmallMaxVolume.setHeight(8.0);
        thingItemForSmallMaxVolume.setWidth(4.0);
        thingItemForSmallMaxVolume.setLength(3.0);

        var listOfMaxVolume = List.of(
                thingItemForSmallMaxVolume,
                Instancio.of(ThingItem.class).create()
        );


        var response = BackpackUtil.generateItemsBasedOnSize(BackpackSize.SMALL, List.of(thingItem), List.of(0.0, 0.0));
        var responseForFood = BackpackUtil.generateItemsBasedOnSize(BackpackSize.SMALL, List.of(foodItem), List.of(0.1, 0.2));
        var responseForItemMaxVolume = BackpackUtil.generateItemsBasedOnSize(BackpackSize.SMALL, listOfMaxVolume, List.of(0.1, 0.2));

        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isEmpty());
        Assertions.assertNotNull(responseForFood);
        Assertions.assertFalse(responseForFood.isEmpty());
        Assertions.assertEquals(1, responseForFood.size());
        Assertions.assertEquals(foodItem, responseForFood.get(0));
        Assertions.assertNotNull(responseForItemMaxVolume);
        Assertions.assertFalse(responseForItemMaxVolume.isEmpty());
        Assertions.assertEquals(1, responseForItemMaxVolume.size());
        Assertions.assertEquals(thingItemForSmallMaxVolume, responseForItemMaxVolume.get(0));
    }

    @Test
    void testDetermineDimensionsOnSize() {
        var response = BackpackUtil.determineDimensionsOnSize(BackpackSize.SMALL);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(List.of(
                Constants.SMALL_BACKPACK_HALF_HEIGHT,
                Constants.SMALL_BACKPACK_HALF_WIDTH,
                Constants.SMALL_BACKPACK_HALF_LENGTH
        ), response);
    }

    @Test
    void testCalculateVolume() {
        var response = BackpackUtil.calculateVolume(60.0, 30.0, 15.0);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(27000.0, response);
    }

    @Test
    void testCalculateNearestDistance() {
        var response = BackpackUtil.calculateNearestDistance(97);
        var responseForTen = BackpackUtil.calculateNearestDistance(8);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(Distance.HUNDRED, response);
        Assertions.assertNotNull(responseForTen);
        Assertions.assertEquals(Distance.TEN, responseForTen);
    }

}
