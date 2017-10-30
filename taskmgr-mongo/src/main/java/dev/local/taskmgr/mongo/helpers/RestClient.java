package dev.local.taskmgr.mongo.helpers;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestClient {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        HttpMessageConverter<String> stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        List<HttpMessageConverter<?>> httpMessageConverters = new ArrayList<>();
        httpMessageConverters.add(stringHttpMessageConverter);
        return builder
                .messageConverters(httpMessageConverters)
                .build();
    }
}
