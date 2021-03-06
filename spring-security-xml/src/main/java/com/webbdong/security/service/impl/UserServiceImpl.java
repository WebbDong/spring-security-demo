package com.webbdong.security.service.impl;

import com.webbdong.security.dao.UserDao;
import com.webbdong.security.domain.SysRole;
import com.webbdong.security.domain.SysUser;
import com.webbdong.security.service.RoleService;
import com.webbdong.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor_={@Autowired})
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final RoleService roleService;

    private final BCryptPasswordEncoder passwordEncoder;

    @Secured("ROLE_ADMIN")
    @Override
    public void save(SysUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @Secured("ROLE_ADMIN")
    @Override
    public List<SysUser> findAll() {
        return userDao.findAll();
    }

    @Secured("ROLE_ADMIN")
    @Override
    public Map<String, Object> toAddRolePage(Integer id) {
        List<SysRole> allRoles = roleService.findAll();
        List<Integer> myRoles = userDao.findRolesByUid(id);
        Map<String, Object> map = new HashMap<>();
        map.put("allRoles", allRoles);
        map.put("myRoles", myRoles);
        return map;
    }

    @Secured("ROLE_ADMIN")
    @Override
    public void addRoleToUser(Integer userId, Integer[] ids) {
        userDao.removeRoles(userId);
        if (ids.length > 0) {
            for (Integer rid : ids) {
                userDao.addRoles(userId, rid);
            }
        }
    }

    /**
     * ????????????
     * @param username ????????????????????????????????????
     * @return UserDetails ??? Spring Security ?????????????????????
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            // ????????????????????????
            SysUser sysUser = userDao.findByName(username);
            if (sysUser == null) {
                return null;
            }
            // {noop} ??????????????????Spring Security ?????????????????????
//            UserDetails userDetails = new User(sysUser.getUsername(), "{noop}" + sysUser.getPassword(), getAuthorities(sysUser.getRoles()));
            UserDetails userDetails = new User(
                    sysUser.getUsername(),
                    sysUser.getPassword(),
                    sysUser.getStatus() == 1,
                    true,
                    true,
                    true,
                    getAuthorities(sysUser.getRoles()));
            return userDetails;
        } catch (Exception e) {
            e.printStackTrace();
            // ?????? null Spring Security ???????????????????????????
            return null;
        }
    }

    private List<SimpleGrantedAuthority> getAuthorities(List<SysRole> roles) {
        return roles.stream()
                .map(r -> new SimpleGrantedAuthority(r.getRoleName()))
                .collect(Collectors.toList());
    }

}
