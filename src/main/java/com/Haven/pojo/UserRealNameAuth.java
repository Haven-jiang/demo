package com.Haven.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@TableName("UserRealNameAuth")
public class UserRealNameAuth {
    /**
     * 认证信息id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;
    /**
     * 用户真实姓名
     */
    private String realName;
    /**
     * 用户身份证号码
     */
    private String idCardNum;
}
