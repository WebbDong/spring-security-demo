package com.webbdong.oauth2.product.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * @author Webb Dong
 * @date 2021-08-03 3:47 PM
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(securedEnabled = true)
public class Oauth2Config extends ResourceServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        // 指定当前资源的id，必须写！
        resources.resourceId("product-service")
                // 指定保存 Token 的方式
                .tokenStore(tokenStore());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/**").access("#oauth2.hasScope('read')")
                .antMatchers(HttpMethod.POST, "/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.PATCH, "/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.PUT, "/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.DELETE, "/**").access("#oauth2.hasScope('write')")
                .and()
                .headers()
                .addHeaderWriter((request, response) -> {
                    // 允许跨域
                    response.addHeader("Access-Control-Allow-Origin", "*");
                    if ("OPTIONS".equals(request.getMethod())) {
                        // 如果是跨域的预检请求，则原封不动向下传达请求头信息
                        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
                        response.setHeader("Access-Control-Allow-Methods", request.getHeader("Access-Control-Request-Method"));
                    }
                });
    }

}
