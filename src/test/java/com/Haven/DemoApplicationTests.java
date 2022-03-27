package com.Haven;

import com.Haven.pojo.User;
import com.Haven.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
//        userService.insertUser(new User(1001, "root", "asdf1476"));
        System.out.println(userService.selectUserByName("root"));
    }

}
