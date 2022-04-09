package com.Haven.config;

//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@EnableWebSecurity
public class SecurityConfig { //extends WebSecurityConfigurerAdapter

//    //授权
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        //首页所有人可以访问, 功能也只有对应权限的人可以访问
//        http.authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/2").permitAll()
//                .antMatchers("/3").permitAll()
//                .antMatchers("/orders").hasRole("orders")
//                .antMatchers("/bounce").hasRole("bounce")
//                .antMatchers("/user").hasRole("user")
//                .antMatchers("/unique").hasRole("unique")
//                ;
//
//        http.formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/login")
//                .and()
//                .rememberMe()
//                .and()
//                .csrf().disable()
//        ;//没有权限默认跳转到登录页
//
//        http.logout()
//                .deleteCookies("remove")
//                .logoutSuccessUrl("/")
//                .invalidateHttpSession(true);
//    }
//
//
//    //认证
//
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()) //密码编码
//                .withUser("Haven").password(new BCryptPasswordEncoder().encode("asdf1476")).roles("orders")
//                .and()
//                .withUser("Jiang").password(new BCryptPasswordEncoder().encode("asdf1476")).roles("bounce")
//                .and()
//                .withUser("jane").password(new BCryptPasswordEncoder().encode("asdf1476")).roles("user")
//                .and()
//                .withUser("unique").password(new BCryptPasswordEncoder().encode("asdf1476")).roles("unique")
//                .and()
//                .withUser("root").password(new BCryptPasswordEncoder().encode("Justholdway521")).roles("orders", "unique", "bounce", "user");
//    }
}
