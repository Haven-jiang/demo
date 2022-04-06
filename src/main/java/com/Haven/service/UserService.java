package com.Haven.service;

import com.Haven.pojo.UserAuth;

import java.util.List;

public interface UserService {
    void register(UserAuth userAuth);
    void deleteUserById(Integer id);
    void updateUser(UserAuth userAuth);
    List<UserAuth> selectUserByName(String username);
    List<UserAuth> selectUserById(Integer id);
    List<UserAuth> selectUserAll();
    String getPrems(UserAuth userAuth);
}
