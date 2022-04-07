package com.Haven.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Goods {
    /**
     * 商品唯一标识
     */
    private Integer id;
    /**
     * 商品名
     */
    private String goodsName;

    /**
     * 商品价格(单位, 价格)
     * 如: 5元/箱
     * Map<Integer, Integer>
     *    <sellingUnit, price>
     */
    private Map<Integer, Integer> price;
    /**
     * 商品类型 - 对应作物三级实现类
     */
    private Integer typeId;
    /**
     * 是否在售
     */
    private Integer isInStack;
    /**
     * 剩余数量 (单位, 数量)
     */
    private Map<Integer, Integer> remainingAmount;
    /**
     * 本商品支持的售卖方式:
     *  00 - 无支持
     *  01 - 支持基础单位售卖: 斤 两 公斤 克
     *  10 - 支持包装单位售卖: 个 箱 袋
     *  11 - 支持基础单位与包装单位售卖
     */
    private Integer sellingUnit;
}
