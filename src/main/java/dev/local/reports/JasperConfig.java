package dev.local.reports;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;

/**
 * Created by wangpeng on 2017/6/16.
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
