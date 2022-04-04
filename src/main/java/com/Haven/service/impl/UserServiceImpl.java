package com.Haven.service.impl;

import com.Haven.mapper.UserMapper;
import com.Haven.pojo.UserAuth;
import com.Haven.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public void insertUser(UserAuth userAuth) {
        userMapper.insertUser(userAuth);
    }

    @Override
    public void deleteUserById(Integer id) {
        userMapper.deleteUserById(id);
    }

    @Override
    public void updateUser(UserAuth userAuth) {
        userMapper.updateUser(userAuth);
    }

    @Override
    public List<UserAuth> selectUserByName(String username) {
        return userMapper.selectUserByName(username);
    }

    @Override
    public List<UserAuth> selectUserById(Integer id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public List<UserAuth> selectUserAll() {
        return userMapper.selectUserAll();
    }

    @Override
    public String getPrems(UserAuth userAuth) {
        return userMapper.selectUserById(userAuth.getId()).get(0).getPrems();
    }
}
