package dev.local.taskmgr.mongo.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // 允许路径中携带 . 否则 . 会被默认认为是后面跟的文件后缀
        configurer.setUseSuffixPatternMatch(false);
    }
}
