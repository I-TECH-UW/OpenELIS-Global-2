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

/**
 * <p>
 * This class configures various components such as message converters and component scanning.
 * It enables Spring Web MVC and sets up JSON message converters using Jackson.
 * </p>
 *
 * <p>
 * The component scanning is set to include packages related to dictionary REST controllers,
 * services, DAO implementations, audit trail DAO implementations, reference tables services,
 * DAO implementations, and history services.
 * </p>
 *
 * <p>
 * The JSON message converter is configured to handle {@code MediaType.APPLICATION_JSON} and
 * set to include non-null values only.
 * </p>
 *
 * <p>
 * The class implements {@link WebMvcConfigurer} to override message converter configuration.
 * </p>
 *
 * @author OpenElisGlobal
 * @see Configuration
 * @see ComponentScan
 * @see EnableWebMvc
 * @see WebMvcConfigurer
 */
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

    /**
     * Provides a JSON message converter configured with Jackson settings.
     *
     * @return MappingJackson2HttpMessageConverter configured for JSON serialization
     */
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

    /**
     * Overrides the default message converters to include {@code StringHttpMessageConverter}
     * and the custom JSON converter.
     *
     * @param converters List of default message converters
     */
    @Override
    public void configureMessageConverters(@NonNull List<HttpMessageConverter<?>> converters) {
        WebMvcConfigurer.super.configureMessageConverters(converters);
        converters.add(new StringHttpMessageConverter());
        converters.add(jsonConverter());
    }
}
