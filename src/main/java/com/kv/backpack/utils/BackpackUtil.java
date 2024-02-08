package com.kv.backpack.utils;

import com.kv.backpack.Constants;
import com.kv.backpack.domain.entity.common.ItemSize;
import com.kv.backpack.domain.entity.common.enumeration.BackpackSize;
import com.kv.backpack.domain.entity.common.enumeration.Distance;
import lombok.experimental.UtilityClass;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class BackpackUtil {
    public static BackpackSize determineSizeOnSeason(Clock clock) {
        final String season = getCurrentSeasonName(clock);

        return switch (season) {
            case "Winter" -> BackpackSize.LARGE;
            case "Spring", "Autumn" -> BackpackSize.MEDIUM;
            case "Summer" -> BackpackSize.SMALL;
            default -> throw new IllegalStateException("Unexpected value: " + season);
        };
    }

    public static String getCurrentSeasonName(Clock clock) {
        Month month = Month.values()[LocalDate.now(clock).getMonthValue() - 1];

        return switch (month) {
            case DECEMBER, JANUARY, FEBRUARY -> "Winter";
            case MARCH, APRIL, MAY -> "Spring";
            case JUNE, JULY, AUGUST -> "Summer";
            case SEPTEMBER, OCTOBER, NOVEMBER -> "Autumn";
        };
    }

    public static <T extends ItemSize> List<T> generateItemsBasedOnSize(BackpackSize backpackSize, List<T> listOfItems, List<Double> extraSizeDependingOnDistance) {
        List<T> selectedItems = new ArrayList<>();
        var volumeOfBackpack = determineVolumeOnSize(backpackSize);
        var maxWeight = backpackSize.getValue();
        var currentWeight = 0.0 - extraSizeDependingOnDistance.get(0);
        var currentVolume = 0.0 - extraSizeDependingOnDistance.get(1);

        for (T item : listOfItems) {
            double volumeOfItem = calculateVolume(item.getHeight(), item.getWidth(), item.getLength());
            if (((currentWeight + item.getWeight()) <= maxWeight) && ((currentVolume + volumeOfItem) <= volumeOfBackpack)) {
                selectedItems.add(item);
                currentWeight += item.getWeight();
                currentVolume += volumeOfItem;
            }
        }

        return selectedItems;
    }

    public static List<Double> determineDimensionsOnSize(BackpackSize backpackSize) {
        return switch (backpackSize) {
            case SMALL ->
                    List.of(Constants.SMALL_BACKPACK_HALF_HEIGHT, Constants.SMALL_BACKPACK_HALF_WIDTH, Constants.SMALL_BACKPACK_HALF_LENGTH);
            case MEDIUM ->
                    List.of(Constants.MEDIUM_BACKPACK_HALF_HEIGHT, Constants.MEDIUM_BACKPACK_HALF_WIDTH, Constants.MEDIUM_BACKPACK_HALF_LENGTH);
            case LARGE ->
                    List.of(Constants.LARGE_BACKPACK_HALF_HEIGHT, Constants.LARGE_BACKPACK_HALF_WIDTH, Constants.LARGE_BACKPACK_HALF_LENGTH);
        };
    }

    public static Double calculateVolume(Double height, Double width, Double length) {
        return height * width * length;
    }

    public static Distance calculateNearestDistance(int km) {
        Distance nearestDistance = Distance.HUNDRED;
        int minDifference = Integer.MAX_VALUE;

        for (Distance distance : Distance.values()) {
            int difference = Math.abs(distance.getValue() - km);
            if (difference < minDifference) {
                minDifference = difference;
                nearestDistance = distance;
            }
        }
        return nearestDistance;
    }

    private Double determineVolumeOnSize(BackpackSize backpackSize) {
        return switch (backpackSize) {
            case SMALL ->
                    calculateVolume(Constants.SMALL_BACKPACK_HALF_HEIGHT, Constants.SMALL_BACKPACK_HALF_WIDTH, Constants.SMALL_BACKPACK_HALF_LENGTH);
            case MEDIUM ->
                    calculateVolume(Constants.MEDIUM_BACKPACK_HALF_HEIGHT, Constants.MEDIUM_BACKPACK_HALF_WIDTH, Constants.MEDIUM_BACKPACK_HALF_LENGTH);
            case LARGE ->
                    calculateVolume(Constants.LARGE_BACKPACK_HALF_HEIGHT, Constants.LARGE_BACKPACK_HALF_WIDTH, Constants.LARGE_BACKPACK_HALF_LENGTH);
        };
    }
}
