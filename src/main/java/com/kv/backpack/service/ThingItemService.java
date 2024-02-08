package com.kv.backpack.service;

import com.kv.backpack.Constants;
import com.kv.backpack.domain.dto.ThingItemDto;
import com.kv.backpack.domain.entity.things.ThingItem;
import com.kv.backpack.exception.ApplicationException;
import com.kv.backpack.mapper.ThingItemMapper;
import com.kv.backpack.repository.ThingItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ThingItemService {

    private final ThingItemRepository thingItemRepository;


    public List<ThingItemDto> getAllThingItems() {
        return thingItemRepository.findAll().stream()
                .map(ThingItemMapper::toThingItemDto)
                .toList();
    }

    public List<ThingItem> getThingItemsSortBySeason(String season) {
        var items = thingItemRepository.findAll();

        List<ThingItem> filteredItems = items.stream()
                .filter(item -> item.getSeason().equalsIgnoreCase(season) || item.getSeason().equalsIgnoreCase(Constants.ALL_SEASONS))
                .collect(Collectors.toList());

        Comparator<ThingItem> comparator = Comparator.comparing(item -> {
            if (item.getSeason().equalsIgnoreCase(season)) {
                return 0;
            } else {
                return 1;
            }
        });

        filteredItems.sort(comparator);

        return filteredItems;
    }

    @Transactional
    public ThingItemDto addItem(ThingItemDto thingItemDto) {
        throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.NOT_IMPLEMENTED);
    }
}
