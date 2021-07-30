package com.webbdong.boot.security.auth.filter;

import com.webbdong.boot.security.auth.domain.SysRole;
import com.webbdong.boot.security.auth.domain.SysUser;
import com.webbdong.boot.security.auth.util.RsaKeyHolder;
import com.webbdong.boot.security.common.consts.JwtConsts;
import com.webbdong.boot.security.common.enums.ExpirationTimeUnit;
import com.webbdong.boot.security.common.model.Response;
import com.webbdong.boot.security.common.util.JsonUtils;
import com.webbdong.boot.security.common.util.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * JWT 认证过滤器
 * @author: Webb Dong
 * @date: 2021-07-29 7:39 PM
 */
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    /**
     * 接收解析用户凭证，并认证
     */
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        final String json = IOUtils.toString(request.getInputStream(), Charset.forName("utf-8"));
        final SysUser sysUser = JsonUtils.parseObj(json, SysUser.class);
        try {
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(sysUser.getUsername(), sysUser.getPassword()));
        } catch (Exception e) {
            errorResponse(response, HttpServletResponse.SC_UNAUTHORIZED);
            throw new RuntimeException(e);
        }
    }

    /**
     * 认证成功后，生成 JWT，返回给客户端
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        final SysUser sysUser = SysUser.builder()
                .username(authResult.getName())
                .roles((List<SysRole>) authResult.getAuthorities())
                .build();
        final String jwt = JwtUtils.generateJwt(
                JwtConsts.USER_INFO_PROPERTY_NAME,
                JsonUtils.toJson(sysUser),
                RsaKeyHolder.INSTANCE.getJwtPrivateKey(),
                1,
                ExpirationTimeUnit.MINUTES);
        successResponse(response, jwt);
    }

    @SneakyThrows
    private void successResponse(HttpServletResponse response, Object data) {
        response.setContentType("application/json;charset=utf8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(JsonUtils.toJson(Response.builder()
                .code(HttpServletResponse.SC_OK)
                .msg("账号或密码错误！")
                .data(data)
                .build()));
        response.getWriter().flush();
        response.getWriter().close();
    }

    @SneakyThrows
    private void errorResponse(HttpServletResponse response, int statusCode) {
        response.setContentType("application/json;charset=utf8");
        response.setStatus(statusCode);
        response.getWriter().write(JsonUtils.toJson(Response.builder()
                .code(statusCode)
                .msg("账号或密码错误！")
                .build()));
        response.getWriter().flush();
        response.getWriter().close();
    }

}
