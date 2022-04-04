package com.Haven.pojo;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAuth implements Serializable {
    /**
     * userAuth 标识
     */
    @TableId(value = "id", type = IdType.AUTO)
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
    private Integer userinfoId;
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
    private LocalDateTime createTime;
    /**
     * 上次更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 上次登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * createTime - 转Json存入数据库
     * @return
     */
    public String getCreateTimeJson() {
        return JSON.toJSONString(createTime).formatted("dd-MM-yyyy");
    }

    /**
     * updateTime - 转Json存入数据库
     * @return
     */
    public String getUpdateTimeJson() {
        return JSON.toJSONString(updateTime).formatted("dd-MM-yyyy");
    }

    /**
     * lastLoginTime - 转Json存入数据库
     * @return
     */
    public String getLastLoginTimeJson() {
        return JSON.toJSONString(lastLoginTime).formatted("dd-MM-yyyy");
    }
}
