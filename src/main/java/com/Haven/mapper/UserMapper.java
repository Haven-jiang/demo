package com.Haven.mapper;

import com.Haven.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    void insertUser(User user);
    void deleteUserById(@Param("userid") Integer id);
    void updateUser(User user);
    List<User> selectUserByName(@Param("username") String username);
    List<User> selectUserById(@Param("userid") Integer id);
    List<User> selectUserAll();
}
