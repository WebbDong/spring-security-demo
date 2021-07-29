package com.webbdong.boot.security.service;

import com.webbdong.boot.security.domain.SysRole;

import java.util.List;

public interface RoleService {

    void save(SysRole role);

    List<SysRole> findAll();

}
