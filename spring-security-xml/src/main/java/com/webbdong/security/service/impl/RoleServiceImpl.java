package com.webbdong.security.service.impl;

import com.webbdong.security.dao.RoleDao;
import com.webbdong.security.domain.SysRole;
import com.webbdong.security.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor(onConstructor_={@Autowired})
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    @Secured("ROLE_ADMIN")
    @Override
    public void save(SysRole role) {
        roleDao.save(role);
    }

    @Secured("ROLE_ADMIN")
    @Override
    public List<SysRole> findAll() {
        return roleDao.findAll();
    }

}
