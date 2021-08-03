package com.webbdong.oauth2.server.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUser {

    private Integer id;

    private String username;

    private String password;

    private Integer status;

    private List<SysRole> roles;

}
