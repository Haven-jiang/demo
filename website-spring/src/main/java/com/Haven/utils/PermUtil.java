package com.Haven.utils;

import com.Haven.entity.UserAuth;
import com.Haven.entity.UserRole;
import com.Haven.mapper.UserAuthMapper;
import com.Haven.mapper.UserRoleMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 用户权限管理工具类 PremUtil
 *
 * @author HavenJust
 * @date 19:33 周日 10 四月 2022年
 */

@Component
public class PermUtil {

    @Autowired
    private static UserAuthMapper userAuthMapper;

    @Autowired
    private static UserRoleMapper userRoleMapper;

    public static String getUserPrem(String username) {
        return userRoleMapper.selectOne(new LambdaQueryWrapper<UserRole>()
                                         .select()
                                         .eq(UserRole::getId,
                                                 userAuthMapper.selectOne(new LambdaQueryWrapper<UserAuth>()
                                                 .select()
                                                 .eq(UserAuth::getUsername, username)
                                         ).getUserRoleId())
        ).getPerms();
    }
}
