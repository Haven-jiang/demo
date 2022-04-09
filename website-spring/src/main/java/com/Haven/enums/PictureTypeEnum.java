package com.Haven.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PictureTypeEnum {
    AVATAR(01, "头像"),
    BANNER(02, "轮播图")
    ;

    private Integer type;
    private String describe;
}
