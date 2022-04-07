package com.Haven.service;

import com.Haven.entity.UserAuth;
import com.Haven.vo.UserVo;

import java.util.List;

public interface UserService {
    boolean register(UserAuth userAuth);
    boolean deleteUserById(Integer id);
    boolean updateUser(UserAuth userAuth);
    void updatePassword(UserVo userVo);
    List<UserAuth> selectUserByName(String username);
    List<UserAuth> selectUserById(Integer id);
    List<UserAuth> selectUserAll();
    String getPrems(UserAuth userAuth);
}
