package dev.local.gateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            // 由于使用的是JWT，我们这里不需要csrf
            .csrf().disable()

            .exceptionHandling().and()

            // 基于token，所以不需要session
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

            .authorizeRequests()
            // .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

            // 允许对于网站静态资源的无授权访问
            .antMatchers(
                    HttpMethod.GET,
                    "/",
                    "/*.html",
                    "/favicon.ico",
                    "/**/*.html",
                    "/**/*.css",
                    "/**/*.js"
            ).permitAll()
            // 对于获取token的rest api要允许匿名访问
            .antMatchers("/info/**", "/auth/**", "/quotes/**", "/pdf/**", "/xls/**", "/**").permitAll()
            // 除上面外的所有请求全部需要鉴权认证
            .anyRequest().authenticated();

        // 禁用缓存
        httpSecurity.headers().cacheControl();
    }
}
