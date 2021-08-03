package com.webbdong.oauth2.server.converter;

import com.webbdong.oauth2.dto.SysUserDTO;
import com.webbdong.oauth2.server.domain.SysUser;
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
