package com.Haven.service;

import com.Haven.mapper.UserMapper;
import com.Haven.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface UserService {
    void insertUser(User user);
    void deleteUserById(Integer id);
    void updateUser(User user);
    List<User> selectUserByName(String username);
    List<User> selectUserById(Integer id);
    List<User> selectUserAll();
    String getPrems(User user);
}
