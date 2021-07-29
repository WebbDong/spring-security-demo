package com.webbdong.security.service;

import com.webbdong.security.domain.SysRole;

import java.util.List;

public interface RoleService {

    void save(SysRole role);

    List<SysRole> findAll();

}
