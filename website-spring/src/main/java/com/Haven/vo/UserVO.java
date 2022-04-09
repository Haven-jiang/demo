package com.Haven.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO {
    /**
     * 用户名
     */
    @NotBlank(message = "账号不能为空!")
    @Email(message = "邮箱格式错误!")
    private String username;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空!")
    @Size(min = 8, message = "密码不能少于8位!")
    private String password;
    /**
     * 短信验证码
     */
    @NotBlank(message = "验证码不能为空")
    private String messageCode;
}
