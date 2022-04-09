package com.Haven.service.impl;

import com.Haven.dto.EmailDTO;
import com.Haven.enums.StatusCodeEnum;
import com.Haven.exception.MyException;
import com.Haven.mapper.*;
import com.Haven.entity.UserAuth;
import com.Haven.entity.UserInfo;
import com.Haven.entity.UserRole;
import com.Haven.service.RedisService;
import com.Haven.service.UserService;
import com.Haven.utils.RandomUtil;
import com.Haven.vo.UserVO;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.Haven.constant.MQPrefixConst.EMAIL_EXCHANGE;
import static com.Haven.constant.RedisPrefixConst.CODE_EXPIRE_TIME;
import static com.Haven.constant.RedisPrefixConst.USER_CODE_KEY;
import static com.Haven.utils.OtherUtil.checkEmail;

@Service
public class UserServiceImpl implements UserService {
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
    @Autowired
    RedisService redisService;


    @Override
    public void sendCode(String username) {
        // 校验账号是否合法
        if (!checkEmail(username)) {
            throw new MyException("请输入正确邮箱");
        }
        // 生成六位随机验证码发送
        String code = RandomUtil.getRandomId(6).toString();
        // 发送验证码
        EmailDTO emailDTO = EmailDTO.builder()
                .receivers(username)
                .subject("验证码")
                .content("您的验证码为 " + code + " 有效期15分钟，请不要告诉他人哦！")
                .build();
        rabbitTemplate.convertAndSend(EMAIL_EXCHANGE, "email", new Message(JSON.toJSONBytes(emailDTO), new MessageProperties()));
        // 将验证码存入redis，设置过期时间为15分钟
        redisService.set(USER_CODE_KEY + username, code, CODE_EXPIRE_TIME);
    }


    /**
     * user 只需要初始化email password
     */
    @Override
    public void register(UserVO user, String code) {

        if (!code.equals(redisService.get(USER_CODE_KEY + user.getUsername()).toString())) throw new MyException(StatusCodeEnum.CODE_NO_EQUAL);
        if (!checkUser(user)) throw new MyException(StatusCodeEnum.USERNAME_EXIST);

        UserInfo userInfo = UserInfo.builder()
                .nickname(RandomUtil.getRandomNick())
                .email(user.getUsername())
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
                        .password(user.getPassword())
                        .build()
        );
    }

    @Override
    public boolean deleteUserById(Integer id) {
        UserAuth userAuth = userAuthMapper.selectById(id);
        UserInfo userInfo = userInfoMapper.selectById(userAuth.getUserInfoId());
        userRoleMapper.deleteById(userAuth.getUserRoleId());
        userInfoMapper.deleteById(userAuth.getUserInfoId());
        if (userAuth.getUserSellerId() != 0) userSellerMapper.deleteById(userAuth.getUserSellerId());
        if (userInfo.getRealNameAuthId() != 0) userRealNameAuthMapper.deleteById(userInfo.getRealNameAuthId());
        userAuthMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean updateUser(UserAuth userAuth) {
        userAuthMapper.updateById(userAuth);
        return true;
    }

    @Override
    public void updatePassword(UserVO userVo) {
//        UserAuth userAuth = userAuthMapper.selectById(id);
//        if (password.equals(userAuth.getPassword())) return 1; //密码与之前相等
//        userAuth.setPassword(password);
//        userAuthMapper.update(userAuth, new AbstractLambdaWrapper<UserAuth>())
    }

    private boolean checkUser(UserVO user) {

        UserAuth userAuth = userAuthMapper.selectOne(new LambdaQueryWrapper<UserAuth>()
                .select(UserAuth::getUsername)
                .eq(UserAuth::getUsername, user.getUsername())
        );
        return Objects.nonNull(userAuth);
    }


    @Override
    public List<UserAuth> selectUserByName(String username) {
        return new ArrayList<>();
    }

    @Override
    public List<UserAuth> selectUserById(Integer id) {
        return new ArrayList<>();
    }

    @Override
    public List<UserAuth> selectUserAll() {
        return new ArrayList<>();
    }

    @Override
    public String getPrems(UserAuth userAuth) {

        return userRoleMapper.selectById(userAuth.getUserRoleId()).getPerms();
    }
}
