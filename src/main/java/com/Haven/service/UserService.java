package com.Haven.service;

import com.Haven.entity.UserAuth;
import com.Haven.vo.UserVO;

import java.util.List;

public interface UserService {

    void register(UserVO userAuth, String code);

    boolean deleteUserById(Integer id);
    boolean updateUser(UserAuth userAuth);
    void updatePassword(UserVO userVo);
    void sendCode(String username);
    List<UserAuth> selectUserByName(String username);
    List<UserAuth> selectUserById(Integer id);
    List<UserAuth> selectUserAll();
    String getPrems(UserAuth userAuth);
}
