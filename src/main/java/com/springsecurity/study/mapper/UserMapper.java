package com.springsecurity.study.mapper;

import com.springsecurity.study.bean.Role;
import com.springsecurity.study.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author jhye4
 * @date 2020/11/23 11:17
 */
@Mapper
public interface UserMapper {

    /**
     * 通过username获取User信息
     * @param username
     * @return
     */
    User loadUserByUserName(String username);

    /**
     * 通过Id获取用户角色信息
     * @param id
     * @return
     */
    List<Role> getUserRolesByUid(Integer id);
}
