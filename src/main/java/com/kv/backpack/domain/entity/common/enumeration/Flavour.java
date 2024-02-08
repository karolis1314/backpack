package com.kv.backpack.domain.entity.common.enumeration;

import com.kv.backpack.Constants;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Flavour {

    SALTY(String.format(Constants.DISH_DESCRIPTION, "salty")),
    SWEET(String.format(Constants.DISH_DESCRIPTION, "sweet")),
    SOUR(String.format(Constants.DISH_DESCRIPTION, "sour")),
    SPICY(String.format(Constants.DISH_DESCRIPTION, "spicy")),
    BLAND(String.format(Constants.DISH_DESCRIPTION, "bland")),
    SAVORY(String.format(Constants.DISH_DESCRIPTION, "savory")),
    BITTER(String.format(Constants.DISH_DESCRIPTION, "bitter")),
    NUTTY(String.format(Constants.DISH_DESCRIPTION, "nutty"));

    private final String value;
}
