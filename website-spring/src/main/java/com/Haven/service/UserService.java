package com.Haven.service;

import com.Haven.entity.UserAuth;
import com.Haven.vo.UserVO;

import java.util.List;

public interface UserService {

    void register(UserVO userAuth);

    boolean deleteUserById(Integer id);
    boolean updateUser(UserAuth userAuth);
    void updatePassword(UserVO userVo);
    void sendCode(String username);

    String getUserPerm(String username);

    UserAuth selectUserByName(String username);
    UserAuth selectUserById(Integer id);
    List<UserAuth> selectUserAll();
}
