package com.Haven.config;

import com.Haven.pojo.User;
import com.Haven.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.Md5CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//自定义的UserRealm extends AuthorizingRealm
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权doGetAuthorizationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        info.addStringPermission(currentUser.getPrems());
//        info.addStringPermission("user:add");
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了=>认证doGetAuthorizationInfo");
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        List<User> users = userService.selectUserByName(userToken.getUsername());
        User lastUser = new User();
        String password = String.valueOf(userToken.getPassword());
        if (users.isEmpty()) {
            return null;
        }
        for (User user : users) {
            if (password.equals(user.getPassword())) lastUser = user;
        }
        return new SimpleAuthenticationInfo(lastUser, lastUser.getPassword(),"");
    }
}
