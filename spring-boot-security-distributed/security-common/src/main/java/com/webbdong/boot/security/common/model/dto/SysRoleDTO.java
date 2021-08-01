package com.webbdong.boot.security.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author Webb Dong
 * @date 2021-07-31 1:29 PM
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleDTO implements GrantedAuthority {

    private Integer id;

    private String roleName;

    private String roleDesc;

    @Override
    public String getAuthority() {
        return roleName;
    }

}
