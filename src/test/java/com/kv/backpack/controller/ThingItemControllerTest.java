package com.kv.backpack.controller;

import com.kv.backpack.Constants;
import com.kv.backpack.domain.dto.ThingItemDto;
import com.kv.backpack.exception.ApplicationException;
import com.kv.backpack.service.ThingItemService;
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
public class ThingItemControllerTest {

    @Mock
    private ThingItemService thingItemService;

    @InjectMocks
    private ThingItemController thingItemController;


    @Test
    void testGetThingItems() {
        Mockito.when(thingItemService.getAllThingItems()).thenReturn(List.of(new ThingItemDto()));

        var response = thingItemController.getThingItems();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals(ThingItemDto.class, response.get(0).getClass());
    }

    @Test
    void testAddItem_Failure() {
        var inputDto = new ThingItemDto();

        Mockito.when(thingItemService.addItem(inputDto))
                .thenThrow(new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.NOT_IMPLEMENTED));

        var exception = Assertions.assertThrows(ApplicationException.class, () -> thingItemController.addItem(inputDto));

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getCode());
        Assertions.assertEquals(Constants.NOT_IMPLEMENTED, exception.getMessage());
    }
}
