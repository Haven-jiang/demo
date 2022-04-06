package com.Haven.service.impl;

import com.Haven.mapper.*;
import com.Haven.pojo.UserAuth;
import com.Haven.pojo.UserInfo;
import com.Haven.pojo.UserRole;
import com.Haven.service.UserService;
import com.Haven.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public void register(UserAuth userAuth) {
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
    }

    @Override
    public void deleteUserById(Integer id) {
        UserAuth userAuth = userAuthMapper.selectById(id);
        UserRole userRole = userRoleMapper.selectById(userAuth.getUserRoleId());
        userRoleMapper.deleteById(userAuth.getUserRoleId());
        userInfoMapper.deleteById(userAuth.getUserInfoId());
        if (userAuth.getUserSellerId() != 0) userSellerMapper.deleteById(userAuth.getUserSellerId());
        if (userAuth.getUserSellerId() != 0) userSellerMapper.deleteById(userRole.);
        userAuthMapper.deleteById(id);
    }

    @Override
    public void updateUser(UserAuth userAuth) {
        userAuthMapper.updateById(userAuth);
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
