package com.webbdong.boot.security.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author Webb Dong
 * @date 2021-07-31 1:28 PM
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SysUserDTO implements UserDetails {

    private Integer id;

    private String username;

    private String password;

    private Integer status;

    private List<SysRoleDTO> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
