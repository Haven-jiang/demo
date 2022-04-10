package com.Haven.config;

import com.Haven.entity.UserAuth;
import com.Haven.service.UserService;
import com.Haven.utils.PermUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
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
        UserAuth currentUserAuth = (UserAuth) subject.getPrincipal();
//        info.addStringPermission(currentUserAuth.getPrems());
        info.addStringPermission("user:" + currentUserAuth.getUsername());
        info.addStringPermission(userService.getUserPerm(currentUserAuth.getUsername()));
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了=>认证doGetAuthorizationInfo");
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        UserAuth userAuth = userService.selectUserByName(userToken.getUsername());
        if (userAuth == null) return null;
        return new SimpleAuthenticationInfo(userAuth, userAuth.getPassword(),"");
    }
}
