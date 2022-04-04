package com.Haven.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    /**
     * userRole id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * User 用户唯一标识
     */
    private Integer userId;
    /**
     * 用户权限
     */
    private String perms;
}
