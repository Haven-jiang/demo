package com.Haven.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean("ShiroFilterFactoryBean")
    //ShiroFilterFactoryBean
    public ShiroFilterFactoryBean getshiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(defaultWebSecurityManager);

        Map<String, String> map = new LinkedHashMap<>();

        map.put("/pages/NewOrders", "perms[user:orders]");
        map.put("/pages/BounceRate", "perms[user:bounce]");
        map.put("/pages/UserRegistrations", "perms[user:user]");
        map.put("/pages/UniqueVisitors", "perms[user:unique]");
//        map.put("/pages/menu", "perms[user:menu]");
        map.put("/pages/*", "authc");

        bean.setFilterChainDefinitionMap(map);
        bean.setLoginUrl("/login");
//        bean.set("/login");
        bean.setUnauthorizedUrl("/noAuth");
        return bean;
    }

    @Bean(name="securityManager")
    //DefaultWebSecurityManager
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联userRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //创建realm对象
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    @Bean //整合ShiroDialect : shiro thymeleaf
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }
}
