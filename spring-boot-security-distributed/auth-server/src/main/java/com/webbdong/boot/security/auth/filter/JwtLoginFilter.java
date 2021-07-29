package com.webbdong.boot.security.auth.filter;

import com.webbdong.boot.security.auth.domain.SysUser;
import com.webbdong.boot.security.common.util.JsonUtils;
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

/**
 * @author: Webb Dong
 * @date: 2021-07-29 7:39 PM
 */
@AllArgsConstructor
@NoArgsConstructor
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    /**
     * 接收解析用户凭证，并认证
     */
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        final String json = IOUtils.toString(request.getInputStream(), Charset.forName("utf-8"));
        final SysUser sysUser = JsonUtils.parseObj(json, SysUser.class);
        try {
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(sysUser.getUsername(), sysUser.getPassword()));
        } catch (Exception e) {
            errorResponse(response);
            throw new RuntimeException(e);
        }
    }

    private void errorResponse(HttpServletResponse response) {
        response.setContentType("application/json;charset=utf8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

    /**
     * 认证成功后，生成 JWT，返回给客户端
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
    }

}
