package com.Haven.service.impl;

import com.Haven.mapper.UserMapper;
import com.Haven.pojo.User;
import com.Haven.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        userMapper.deleteUserById(id);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public List<User> selectUserByName(String username) {
        return userMapper.selectUserByName(username);
    }

    @Override
    public List<User> selectUserById(Integer id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public List<User> selectUserAll() {
        return userMapper.selectUserAll();
    }

    @Override
    public String getPrems(User user) {
        return userMapper.selectUserById(user.getId()).get(0).getPrems();
    }
}
