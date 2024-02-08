package com.kv.backpack.domain.entity.common.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Material {
    METAL("metal"),
    PLASTIC("plastic"),
    PAPER("paper"),
    NYLON("nylon"),
    COTTON("cotton"),
    WOOL("wool"),
    CLOTH("cloth");

    private final String value;
}
