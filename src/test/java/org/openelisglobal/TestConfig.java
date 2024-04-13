package org.openelisglobal;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan(basePackages = {
        "org.openelisglobal.dictionary.rest.controller",
        "org.openelisglobal.dictionary.service",
        "org.openelisglobal.dictionary.daoimpl",
        "org.openelisglobal.audittrail.daoimpl",
        "org.openelisglobal.referencetables.service",
        "org.openelisglobal.referencetables.daoimpl",
        "org.openelisglobal.history.service",

})
@EnableWebMvc
public class TestConfig implements WebMvcConfigurer {
    @Bean
    public MappingJackson2HttpMessageConverter jsonConverter() {
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);

        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);

        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter(builder.build());
        jsonConverter.setSupportedMediaTypes(supportedMediaTypes);
        return jsonConverter;
    }

    @Override
    public void configureMessageConverters(@NonNull List<HttpMessageConverter<?>>  converters) {
        WebMvcConfigurer.super.configureMessageConverters(converters);
        converters.add(new StringHttpMessageConverter());
        converters.add(jsonConverter());
    }
}

