package com.Haven.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BaseSellingUnitEnum {
    GRAM(1, "克"),
    LIANG(50, "两"),
    JIN(500, "斤"),
    KILOGRAM(1000, "公斤"),
    ;

    private final Integer type;
    private final String describe;
}
