package com.kv.backpack.controller;

import com.kv.backpack.domain.dto.ThingItemDto;
import com.kv.backpack.service.ThingItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Thing", description = "Thing API")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/thing")
public class ThingItemController {

    private final ThingItemService thingItemService;

    @Operation(summary = "Get all thing items")
    @GetMapping
    @ResponseBody
    public List<ThingItemDto> getThingItems() {
        return thingItemService.getAllThingItems();
    }

    @Operation(summary = "Add a thing item",
            description = "P.S : Unfortunately, we can't add a thing item at the moment. We are working on it.")
    @PostMapping
    @ResponseBody
    public ThingItemDto addItem(@RequestBody ThingItemDto thingItemDto) {
        return thingItemService.addItem(thingItemDto);
    }
}
