package com.Haven.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文章类型枚举
 */
@Getter
@AllArgsConstructor
public enum ArticleTypeEnum {
    /**
     * 商品详情
     */
    PRODUCT_DETAILS(1, "商品详情");

    /**
     * 文章类型
     */
    private final Integer type;
    /**
     * 描述
     */
    private final String describe;
}
