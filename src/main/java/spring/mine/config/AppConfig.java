package spring.mine.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import spring.mine.interceptor.ModuleAuthenticationInterceptor;
import spring.mine.interceptor.PageAttributesInterceptor;
import spring.mine.interceptor.UrlErrorsInterceptor;
import spring.mine.security.SecurityConfig;

@EnableWebMvc
@Configuration
@ComponentScan({"spring", "us.mn.state.health.lims"})
public class AppConfig implements WebMvcConfigurer {

	@Autowired
	ModuleAuthenticationInterceptor moduleAuthenticationInterceptor;
	@Autowired
	UrlErrorsInterceptor urlLocatedErrorsInterceptor;
	@Autowired
	PageAttributesInterceptor pageAttributesInterceptor;
	@Autowired
	RequestMappingHandlerMapping requestMappingHandlerMapping;

	@Bean
	public ViewResolver viewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass(TilesView.class);
		return viewResolver;
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfig = new TilesConfigurer();
		String[] tilesFiles = new String[] { "classpath:/tiles/tiles-defs.xml",
				"classpath:/tiles/tiles-globalOpenELIS.xml" };
		tilesConfig.setDefinitions(tilesFiles);
		return tilesConfig;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/languages/message");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setUseCodeAsDefaultMessage(true);
		return messageSource;
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(moduleAuthenticationInterceptor).addPathPatterns("/**")
				.excludePathPatterns(SecurityConfig.OPEN_PAGES).excludePathPatterns(SecurityConfig.RESOURCE_PAGES)
				.excludePathPatterns(SecurityConfig.AUTH_OPEN_PAGES);
		registry.addInterceptor(urlLocatedErrorsInterceptor).addPathPatterns("/**");
		registry.addInterceptor(pageAttributesInterceptor).addPathPatterns("/**");
	}

}
