package com.Haven.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSeller {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 商品列表
     */
    private List<Integer> goodsList;
    /**
     * 卖出记录
     */
    private List<Integer> sellingList;
    /**
     * 满意度等级
     */
    private Integer satisfactionLevel;
    /**
     * 默认发货地址
     */
    private String defaultShipAddress;
}