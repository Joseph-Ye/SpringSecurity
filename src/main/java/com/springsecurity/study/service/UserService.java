package com.springsecurity.study.service;

import com.springsecurity.study.bean.Role;
import com.springsecurity.study.bean.User;
import com.springsecurity.study.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

/**
 * @author jhye4
 * @date 2020/11/23 11:23
 * 定义的 UserService 实现 UserDetailsService 接口，并实现该接口中的 loadUserByUsername 方法，该方法将在用户登录时自动调用
 *
 * loadUserByUsername 方法的参数就是用户登录时输入的用户名，通过用户名去数据库中查找用户：
 *
 * 如果没有查找到用户，就抛出一个账户不存在的异常。
 * 如果查找到了用户，就继续查找该用户所具有的角色信息，并将获取到的 user 对象返回，再由系统提供的 DaoAuthenticationProvider类去比对密码是否正确。
 */
@org.springframework.stereotype.Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUserName(username);
        if(null == user){
            throw new UsernameNotFoundException("账户不存在");
        }

        // 手动设置密码
        String encodePassword = passwordEncoder.encode(user.getPassword());
        System.out.println("加密后的密码： "+encodePassword);
        user.setPassword(encodePassword);
        List<Role> roles = userMapper.getUserRolesByUid(user.getId());
        user.setUserRoles(roles);
        System.out.println("该用户的角色为：" + user.getUserRoles().get(0).getName());
        return user;
    }
}
