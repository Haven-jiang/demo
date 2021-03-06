package com.Haven.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户
 *
 * @author HavenJust
 * @date 2022/4/7
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("user_auth")
public class UserAuth implements Serializable {
    /**
     * userAuth 标识
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;
    /**
     * 用户名 不是昵称!
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 登录类型 - wechat qq weibo telephoneNumber email ...
     */
    private Integer loginType;
    /**
     * 指向userInfo
     */
    private Integer userInfoId;
    /**
     * 登录ip
     */
    private String ipAddress;
    /**
     * 登录ip库
     * - json
     */
    private String ipSource;
    /**
     * 指向userRole
     */
    private Integer userRoleId;
    /**
     * 指向userSeller
     * 若不是商家 参数为零
     */
    private Integer userSellerId;
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
     * 上次登录时间
     */
    private LocalDateTime lastLoginTime;
}
