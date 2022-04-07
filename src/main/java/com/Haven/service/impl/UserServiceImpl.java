package com.Haven.service.impl;

import com.Haven.mapper.*;
import com.Haven.entity.UserAuth;
import com.Haven.entity.UserInfo;
import com.Haven.entity.UserRole;
import com.Haven.service.UserService;
import com.Haven.utils.RandomUtil;
import com.Haven.vo.UserVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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


    /**
     * userAuth 只需要初始化email password
     */
    @Override
    public boolean register(UserAuth userAuth) {
        UserInfo userInfo = UserInfo.builder()
                .nickname(RandomUtil.getRandomNick())
                .email(userAuth.getUsername())
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
        return true;
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
    public void updatePassword(UserVo userVo) {
//        UserAuth userAuth = userAuthMapper.selectById(id);
//        if (password.equals(userAuth.getPassword())) return 1; //密码与之前相等
//        userAuth.setPassword(password);
//        userAuthMapper.update(userAuth, new AbstractLambdaWrapper<UserAuth>())
    }

    private boolean checkUser(UserVo user) {

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
