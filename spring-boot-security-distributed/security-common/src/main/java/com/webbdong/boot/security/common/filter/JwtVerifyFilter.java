package com.webbdong.boot.security.common.filter;

import com.webbdong.boot.security.common.consts.JwtConsts;
import com.webbdong.boot.security.common.model.dto.Payload;
import com.webbdong.boot.security.common.model.dto.SysUserDTO;
import com.webbdong.boot.security.common.model.vo.ResponseVO;
import com.webbdong.boot.security.common.util.JsonUtils;
import com.webbdong.boot.security.common.util.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.PublicKey;

/**
 * JWT 校验过滤器
 * @author: Webb Dong
 * @date: 2021-07-30 2:57 PM
 */
public class JwtVerifyFilter extends BasicAuthenticationFilter {

    private PublicKey publicKey;

    public JwtVerifyFilter(AuthenticationManager authenticationManager, PublicKey publicKey) {
        super(authenticationManager);
        this.publicKey = publicKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        final String token = request.getHeader(JwtConsts.TOKEN_REQUEST_HEADER_NAME);
        if (StringUtils.isBlank(token)) {
            chain.doFilter(request, response);
            unAuthResponse(response, "无权限");
            return;
        }

        try {
            final UsernamePasswordAuthenticationToken auth = getAuthenticationByToken(token);
            // 用户认证信息获取成功后，将 auth 对象存入 SecurityContext 中
            SecurityContextHolder.getContext().setAuthentication(auth);
            chain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            unAuthResponse(response, "Token 过期");
        } catch (Exception e) {
            e.printStackTrace();
            unAuthResponse(response, "请登录");
        }
    }

    /**
     * 根据 Token 获取用户认证信息
     * @param token
     * @return
     */
    private UsernamePasswordAuthenticationToken getAuthenticationByToken(String token) {
        final Payload<SysUserDTO> payload = JwtUtils.parseToken(
                JwtConsts.USER_INFO_PROPERTY_NAME,
                token,
                publicKey,
                SysUserDTO.class);
        final SysUserDTO sysUser = payload.getData();
        if (sysUser != null) {
            return new UsernamePasswordAuthenticationToken(sysUser, null, sysUser.getRoles());
        }
        return null;
    }

    @SneakyThrows
    private void unAuthResponse(HttpServletResponse response, String msg) {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(JsonUtils.toJson(
                ResponseVO.builder()
                        .code(HttpServletResponse.SC_UNAUTHORIZED)
                        .msg(msg)
                        .build()));
        response.getWriter().flush();
        response.getWriter().close();
    }

}
