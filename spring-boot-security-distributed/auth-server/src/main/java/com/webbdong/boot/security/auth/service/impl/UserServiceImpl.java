package com.webbdong.boot.security.auth.service.impl;

import com.webbdong.boot.security.auth.converter.SysUserModelConverter;
import com.webbdong.boot.security.auth.domain.SysUser;
import com.webbdong.boot.security.auth.mapper.UserMapper;
import com.webbdong.boot.security.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        final SysUser sysUser = userMapper.findByName(s);
        return SysUserModelConverter.INSTANCE.sysUserToSysUserDTO(sysUser);
    }

}
