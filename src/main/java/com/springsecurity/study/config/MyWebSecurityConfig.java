package com.springsecurity.study.config;

import com.springsecurity.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author jhye4
 * @date 2020/11/23 10:34
 */
@Configuration
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;
    /**
     * 指定密码加密方式
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
//          return new BCryptPasswordEncoder();
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(charSequence.toString());
            }
        };
    }

    /**
     * 配置用户以及对应的角色
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
//        auth.inMemoryAuthentication().
//                withUser("jhye4").password("123456").roles("admin", "DBA").and()
//                .withUser("tongtong").password("123456").roles("USER");
    }

    /**
     * 配置URL访问权限
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("admin")
                // 两者之一
                .antMatchers("/user/**").access("hasAnyRole('admin','USER')")
                // 两者兼备
                .antMatchers("/db/**").access("hasRole('admin') and hasRole('DBA')")
                // 任意其他请求都需要认证
                .anyRequest().authenticated()
                // 开启表单登录并配置登录接口
                .and().formLogin().loginProcessingUrl("/login").permitAll()
                // 关闭csrf
                .and().csrf().disable();
    }

}
