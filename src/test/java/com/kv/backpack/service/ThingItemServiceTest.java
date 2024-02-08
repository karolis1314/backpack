package com.kv.backpack.service;

import com.kv.backpack.Constants;
import com.kv.backpack.domain.dto.ThingItemDto;
import com.kv.backpack.domain.entity.things.ThingItem;
import com.kv.backpack.exception.ApplicationException;
import com.kv.backpack.repository.ThingItemRepository;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ThingItemServiceTest {

    @Mock
    private ThingItemRepository thingItemRepository;

    @InjectMocks
    private ThingItemService thingItemService;


    @Test
    void testGetAllThingItems() {
        var list = List.of(
                Instancio.of(ThingItem.class).create(),
                Instancio.of(ThingItem.class).create()
        );

        Mockito.when(thingItemRepository.findAll()).thenReturn(list);

        var result = thingItemService.getAllThingItems();

        Mockito.verify(thingItemRepository, Mockito.times(1)).findAll();
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(ThingItemDto.class, result.get(0).getClass());
    }

    @Test
    void testGetThingItemsSortBySeason() {
        var list = List.of(
                Instancio.of(ThingItem.class)
                        .set(Select.field("season"), "summer")
                        .create(),
                Instancio.of(ThingItem.class)
                        .set(Select.field("season"), "all")
                        .create(),
                Instancio.of(ThingItem.class)
                        .set(Select.field("season"), "winter")
                        .create()
        );

        Mockito.when(thingItemRepository.findAll()).thenReturn(list);

        var result = thingItemService.getThingItemsSortBySeason("summer");

        Mockito.verify(thingItemRepository, Mockito.times(1)).findAll();
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(ThingItem.class, result.get(0).getClass());
    }

    @Test
    void testAddItem() {
        var exception = Assertions.assertThrows(ApplicationException.class, () -> thingItemService.addItem(new ThingItemDto()));

        Assertions.assertNotNull(exception);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getCode());
        Assertions.assertEquals(Constants.NOT_IMPLEMENTED, exception.getMessage());
    }
}
