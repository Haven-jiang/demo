package com.Haven;

import com.Haven.mapper.*;
import com.Haven.entity.UserAuth;
import com.Haven.entity.UserInfo;
import com.Haven.entity.UserRole;
import com.Haven.utils.RandomUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    UserAuthMapper userAuthMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    UserRealNameAuthMapper userRealNameAuthMapper;
    @Autowired
    UserSellerMapper userSellerMapper;

    @Test
    void contextLoads() {
        UserAuth userAuth = UserAuth.builder()
                                    .password("asdf1476")
                                    .build();
        UserInfo userInfo = UserInfo.builder()
                                    .nickname(RandomUtil.getRandomNick())
                                    .email("example1@example.com")
                                    .isBan(0)
                                    .build();
        userInfoMapper.insert(userInfo);
        UserRole userRole = UserRole.builder()
                .userId(RandomUtil.getRandomId(10))
                .perms(null)
                .build();
        userRoleMapper.insert(userRole);
        userAuthMapper.insert(
                UserAuth.builder()
                        .userInfoId(userInfo.getId())
                        .userRoleId(userRole.getId())
                        .username(userInfo.getEmail())
                        .password(userAuth.getPassword())
                        .build()
        );
        System.out.println();
//        userService.insertUser(new User(1001, "root", "asdf1476"));
//        System.out.println(userService.selectUserByName("root"));
    }

}
