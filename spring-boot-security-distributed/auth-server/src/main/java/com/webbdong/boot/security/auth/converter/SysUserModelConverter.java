package com.webbdong.boot.security.auth.converter;

import com.webbdong.boot.security.auth.domain.SysUser;
import com.webbdong.boot.security.common.model.dto.SysUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Webb Dong
 * @date 2021-08-01 12:35 AM
 */
@Mapper
public interface SysUserModelConverter {

    SysUserModelConverter INSTANCE = Mappers.getMapper(SysUserModelConverter.class);

    SysUserDTO sysUserToSysUserDTO(SysUser sysUser);

}
