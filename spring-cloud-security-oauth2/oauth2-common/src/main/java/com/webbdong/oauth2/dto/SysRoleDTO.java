package com.webbdong.oauth2.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @Override
    public String getAuthority() {
        return roleName;
    }

}
