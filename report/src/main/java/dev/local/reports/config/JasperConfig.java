package dev.local.reports.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;

/**
 * 配置 JsperReports 使用 reports_views 作为资源文件的名字
 * 在这里 resources/report_views.properties 是 ViewResolver 会
 * 去查找的资源文件。在资源文件中可以配置对应的 view 使用什么 servlet
 */
@Configuration
public class JasperConfig {

    @Bean
    public ResourceBundleViewResolver viewResolver() {
        ResourceBundleViewResolver resolver =  new ResourceBundleViewResolver();
        resolver.setBasename("report_views");
        resolver.setOrder(0);
        return resolver;
    }
}
