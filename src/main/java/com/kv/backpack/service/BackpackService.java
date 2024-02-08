package com.kv.backpack.service;

import com.kv.backpack.domain.dto.BackpackDto;
import com.kv.backpack.domain.entity.Backpack;
import com.kv.backpack.domain.entity.common.enumeration.BackpackSize;
import com.kv.backpack.domain.entity.common.enumeration.Distance;
import com.kv.backpack.domain.entity.food.FoodItem;
import com.kv.backpack.domain.entity.things.ThingItem;
import com.kv.backpack.mapper.BackpackMapper;
import com.kv.backpack.repository.BackpackRepository;
import com.kv.backpack.utils.BackpackUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BackpackService {

    private final BackpackRepository backpackRepository;
    private final ThingItemService thingItemService;
    private final FoodItemService foodItemService;
    private final Clock clock;

    @Transactional
    public BackpackDto createBackpackOnSeason(int km) {
        var seasonName = BackpackUtil.getCurrentSeasonName(clock);
        var backpackSize = BackpackUtil.determineSizeOnSeason(clock);
        var distance = BackpackUtil.calculateNearestDistance(km);
        var extraSizeDependingOnDistance = getExtraSizeDependingOnKm(distance);
        var thingItems = BackpackUtil.generateItemsBasedOnSize(backpackSize, thingItemService.getThingItemsSortBySeason(seasonName), List.of(0.0, 0.0));
        var foodItems = BackpackUtil.generateItemsBasedOnSize(backpackSize, foodItemService.getAllFoodItems(), extraSizeDependingOnDistance);

        var backpack = createBackpackOnSize(
                backpackSize,
                thingItems,
                foodItems,
                BackpackUtil.determineDimensionsOnSize(backpackSize),
                extraSizeDependingOnDistance
        );

        backpackRepository.save(backpack);

        return BackpackMapper.toBackPackDto(backpack);
    }

    public List<BackpackDto> getPreCreatedBackpack() {
        return backpackRepository.findAll().stream()
                .map(BackpackMapper::toBackPackDto)
                .toList();
    }

    private Backpack createBackpackOnSize(BackpackSize backpackSize, List<ThingItem> items,
                                          List<FoodItem> foodItems, List<Double> dimensionsOnSize, List<Double> extraSizeDependingOnDistance) {
        var actualSize = dimensionsOnSize.stream()
                .map(number -> number * 2)
                .toList();

        return Backpack.builder()
                .size(backpackSize)
                .backpackItems(items)
                .foodItems(foodItems)
                .height(actualSize.get(0))
                .width(actualSize.get(1))
                .length(actualSize.get(2))
                .weight(Double.valueOf(backpackSize.getValue()) + extraSizeDependingOnDistance.get(0))
                .build();
    }

    private List<Double> getExtraSizeDependingOnKm(Distance km) {
        return switch (km) {
            case HUNDRED -> List.of(3.0, 1000.0);
            case FIFTY -> List.of(1.5, 500.0);
            case TWENTY_FIVE -> List.of(0.75, 250.0);
            case FIFTEEN -> List.of(0.45, 150.0);
            case TEN -> List.of(0.3, 100.0);
            case FIVE -> List.of(0.15, 50.0);
            case TWO -> List.of(0.06, 20.0);
        };
    }
}
