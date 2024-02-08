package com.kv.backpack.mapper;

import com.kv.backpack.domain.entity.things.ThingItem;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ThingItemMapperTest {

    @Test
    void testToThingItemDto() {
        var thingItem = Instancio.of(ThingItem.class)
                .set(Select.field("id"), 1L)
                .set(Select.field("name"), "Lighter")
                .set(Select.field("fitsToSmallSection"), true)
                .create();

        var thingItemDto = ThingItemMapper.toThingItemDto(thingItem);

        Assertions.assertNotNull(thingItemDto);
        Assertions.assertEquals(1L, thingItemDto.getId());
        Assertions.assertEquals("Lighter", thingItemDto.getName());
        Assertions.assertTrue(thingItemDto.getFitsToSmallSection());
    }
}
