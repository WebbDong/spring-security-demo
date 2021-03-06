package com.webbdong.security.service;

import com.webbdong.security.domain.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface UserService extends UserDetailsService {

    void save(SysUser user);

    List<SysUser> findAll();

    Map<String, Object> toAddRolePage(Integer id);

    void addRoleToUser(Integer userId, Integer[] ids);

}
