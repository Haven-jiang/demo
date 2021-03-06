package com.Haven.entity;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("user_info")
public class UserInfo implements Serializable {
    /**
     * userInfo id 用于其他实体类取数据
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;
    /**
     * user 邮箱
     */
    private String email;
    /**
     * 储存 picture id - 用户头像
     */
    private Integer avatarId;
    /**
     * 用户简介 - 签名
     */
    private String intro = "在这里写下你的简介";
    /**
     * 用户是否被关小黑屋
     */
    private Integer isBan;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 购物车
     * Integer 储存 GoodsID;
     */
    private List<Integer> cart;
    /**
     * 已购列表
     * Integer 储存 GoodsID;
     */
    private List<Integer> purchasedList;
    /**
     * 用户实名认证信息 - 未认证:0
     */
    private Integer realNameAuthId;
    /**
     * 指向userSeller
     * 若不是商家 参数为零
     */
    private Integer userSellerId;

    private List<String> shipAddressSource;

    private Integer defaultShipAddress;

    /**
     * 用户创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 上次更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * cart - 转json存入数据库
     * @return json对象
     */
    public String getCartJson() {
        return JSON.toJSONString(cart);
    }

    /**
     * purchasedList - 转json存入数据库
     * @return json
     */
    public String getPurchasedListJson() {
        return JSON.toJSONString(purchasedList);
    }

    /**
     * shipAddressSource - 转json存入数据库
     * @return json对象
     */
    public String getShipAddressSourceJson() {
        return JSON.toJSONString(shipAddressSource);
    }

}
