package com.webbdong.security.dao;

import com.webbdong.security.domain.SysRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleDao {

    @Select("SELECT r.id, r.role_name roleName, r.role_desc roleDesc " +
            "FROM sys_role r, sys_user_role ur " +
            "WHERE r.id=ur.rid AND ur.uid=#{uid}")
    List<SysRole> findByUid(Integer uid);

    @Insert("insert into sys_role (role_name, role_desc) values (#{roleName}, #{roleDesc})")
    void save(SysRole role);

    @Select("select id, role_name roleName, role_desc roleDesc from sys_role")
    List<SysRole> findAll();

}
