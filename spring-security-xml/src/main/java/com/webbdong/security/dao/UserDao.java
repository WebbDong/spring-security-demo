package com.webbdong.security.dao;

import com.webbdong.security.domain.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {

    @Select("select * from sys_user where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles", column = "id", javaType = List.class,
                    many = @Many(select = "com.webbdong.security.dao.RoleDao.findByUid"))
    })
    SysUser findByName(String username);

    @Insert("insert into sys_user (username, password) values (#{username}, #{password})")
    void save(SysUser user);

    @Select("select * from sys_user")
    List<SysUser> findAll();

    @Select("SELECT r.id FROM sys_role r, sys_user_role ur " +
            "WHERE ur.rid=r.id AND ur.uid=#{uid}")
    List<Integer> findRolesByUid(Integer id);

    @Delete("delete from sys_user_role where uid=#{userId}")
    void removeRoles(Integer userId);

    @Insert("insert into sys_user_role values (#{uid}, #{rid})")
    void addRoles(@Param("uid") Integer userId, @Param("rid") Integer rid);

}
