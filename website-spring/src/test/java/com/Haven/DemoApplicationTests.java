package com.Haven;

import com.Haven.dto.EmailDTO;
import com.Haven.mapper.*;
import com.Haven.entity.UserAuth;
import com.Haven.entity.UserInfo;
import com.Haven.entity.UserRole;
import com.Haven.service.RedisService;
import com.Haven.service.UserService;
import com.Haven.utils.RandomUtil;
import com.Haven.utils.RedisUtil;
import com.Haven.vo.UserVO;
import com.alibaba.fastjson.JSON;
import org.elasticsearch.common.Rounding;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.Haven.constant.MQPrefixConst.SEND_EXCHANGE;
import static com.Haven.constant.RedisPrefixConst.CODE_EXPIRE_TIME;
import static com.Haven.constant.RedisPrefixConst.USER_CODE_KEY;
import com.Haven.utils.RedisUtil;

import java.util.concurrent.TimeUnit;

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
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
        UserAuth userAuth = UserAuth.builder()
                                    .password("asdf1476")
                                    .build();
        UserInfo userInfo = UserInfo.builder()
                                    .nickname(RandomUtil.getRandomNick())
                                    .email("haven-just@qq.com")
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
    @Test
    void test01() {

        rabbitTemplate.convertAndSend(SEND_EXCHANGE, "email", RandomUtil.getRandomUUID(25));
        System.out.println();
    }

    @Autowired
    UserService userService;

    @Test
    void test_sendmail() {
        rabbitTemplate.convertAndSend("direct_send_exchange", "sms", RandomUtil.getRandomUUID(25));
        userService.sendCode("haven-just@qq.com");
    }

    @Autowired
    RedisService redisService;


    @Test
    void test_register() {
        UserVO build = UserVO.builder()
                .username("haven-just@qq.com")
                .password("asdf1476")
                .messageCode("726993")
                .build();
        userService.register(build);
        System.out.println();
    }

}
