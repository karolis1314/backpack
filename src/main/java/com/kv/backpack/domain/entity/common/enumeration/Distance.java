package com.kv.backpack.domain.entity.common.enumeration;

import com.kv.backpack.exception.ApplicationException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Distance {

    HUNDRED(100),
    FIFTY(50),
    TWENTY_FIVE(25),
    FIFTEEN(15),
    TEN(10),
    FIVE(5),
    TWO(2);

    private final Integer value;

    public static Distance fromValue(Integer value) {
        return Arrays.stream(values())
                .filter(res -> res.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new ApplicationException(HttpStatus.NOT_FOUND, String.format("Unexpected value: %s", value)));
    }
}
