package com.webbdong.boot.security.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysRole {

    private Integer id;

    private String roleName;

    private String roleDesc;

}
