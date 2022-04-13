package com.Haven.service.impl;

import com.Haven.dto.EmailDTO;
import com.Haven.entity.UserAuth;
import com.Haven.entity.UserInfo;
import com.Haven.entity.UserRole;
import com.Haven.enums.StatusCodeEnum;
import com.Haven.exception.MyException;
import com.Haven.mapper.*;
import com.Haven.service.UserService;
import com.Haven.utils.RandomUtil;
import com.Haven.utils.RedisUtil;
import com.Haven.vo.UserVO;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.Haven.constant.MQPrefixConst.SEND_EXCHANGE;
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
        rabbitTemplate.convertAndSend(SEND_EXCHANGE, "email", new Message(JSON.toJSONBytes(emailDTO), new MessageProperties()));
        // 将验证码存入redis，设置过期时间为15分钟
        RedisUtil.StringOps.setEx(USER_CODE_KEY + username, code, CODE_EXPIRE_TIME, TimeUnit.SECONDS);
    }


    /**
     * user 只需要初始化email password
     */
    @Override
    public void register(UserVO user) {

        if (checkUser(user)) throw new MyException(StatusCodeEnum.USERNAME_EXIST);

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

    private Boolean checkUser(UserVO user) {
        if (!user.getMessageCode().equals(RedisUtil.StringOps.get(USER_CODE_KEY + user.getUsername()))) {
            throw new MyException("验证码错误！");
        }
        //查询用户名是否存在
        UserAuth userAuth = userAuthMapper.selectOne(new LambdaQueryWrapper<UserAuth>()
                .select(UserAuth::getUsername)
                .eq(UserAuth::getUsername, user.getUsername())
        );
        return Objects.nonNull(userAuth);
    }

    @Override
    public String getUserPerm(String username) {
        return userRoleMapper.selectOne(new LambdaQueryWrapper<UserRole>()
                .select()
                .eq(UserRole::getId,
                        userAuthMapper.selectOne(new LambdaQueryWrapper<UserAuth>()
                                .select()
                                .eq(UserAuth::getUsername, username)
                        ).getUserRoleId())
        ).getPerms();
    }


    @Override
    public UserAuth selectUserByName(String username) {
        UserAuth userAuth = userAuthMapper.selectOne(new LambdaQueryWrapper<UserAuth>()
                .select()
                .eq(UserAuth::getUsername, username)
        );
        return userAuth;
    }

    @Override
    public UserAuth selectUserById(Integer id) {
        return userAuthMapper.selectOne(new LambdaQueryWrapper<UserAuth>()
                                                .select()
                                                .eq(UserAuth::getId, id)
        );
    }

    @Override
    public List<UserAuth> selectUserAll() {
        return userAuthMapper.selectList(
                new LambdaQueryWrapper<UserAuth>().select()
        );
    }
}
