package com.kv.backpack.controller;

import com.kv.backpack.domain.dto.BackpackDto;
import com.kv.backpack.service.BackpackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Backpack", description = "Backpack API")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/backpack")
public class BackpackController {

    private final BackpackService backpackService;


    @Operation(summary = "Create backpack based on season, and distance in km",
            description = "P.S : Please round up the distance to the nearest whole number")
    @PostMapping("/{km}")
    @ResponseBody
    public BackpackDto createBackpack(@PathVariable int km) {
        return backpackService.createBackpackOnSeason(km);
    }

    @Operation(summary = "Get all created backpacks")
    @GetMapping
    @ResponseBody
    public List<BackpackDto> getPreCreatedBackpack() {
        return backpackService.getPreCreatedBackpack();
    }
}
