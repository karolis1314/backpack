package com.kv.backpack.controller;

import com.kv.backpack.domain.dto.BackpackDto;
import com.kv.backpack.service.BackpackService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BackpackControllerTest {

    @Mock
    private BackpackService backpackService;

    @InjectMocks
    private BackpackController backpackController;


    @Test
    void testCreateBackpack() {
        var km = 10;
        Mockito.when(backpackService.createBackpackOnSeason(km)).thenReturn(new BackpackDto());

        var response = backpackController.createBackpack(km);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(BackpackDto.class, response.getClass());
    }

    @Test
    void testGetPreCreatedBackpack() {
        Mockito.when(backpackService.getPreCreatedBackpack()).thenReturn(List.of(new BackpackDto()));

        var response = backpackController.getPreCreatedBackpack();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.size());
    }
}
