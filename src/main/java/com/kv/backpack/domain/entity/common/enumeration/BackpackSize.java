package com.kv.backpack.domain.entity.common.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BackpackSize {

    SMALL(2),
    MEDIUM(5),
    LARGE(10);

    private final Integer value;
}
