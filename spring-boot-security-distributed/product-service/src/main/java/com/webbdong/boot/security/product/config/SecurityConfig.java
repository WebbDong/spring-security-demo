package com.webbdong.boot.security.product.config;

import com.webbdong.boot.security.common.filter.JwtVerifyFilter;
import com.webbdong.boot.security.common.util.JwtRsaPublicKeyHolder;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @author: Webb Dong
 * @date: 2021-08-02 10:30 AM
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/product/**").hasAnyRole("PRODUCT")
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtVerifyFilter(authenticationManager(), JwtRsaPublicKeyHolder.INSTANCE.getJwtPublicKey()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
