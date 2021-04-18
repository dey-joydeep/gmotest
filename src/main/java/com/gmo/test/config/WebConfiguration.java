package com.gmo.test.config;

import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gmo.test.shared.utils.RequestHandleInterceptor;

/**
 * Springウエブアプリの構成クラス
 * 
 * @author Joydeep Dey
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new RequestHandleInterceptor());
	}

	/**
	 * {@link LocalValidatorFactoryBean}の{@link Validator}インスタンスを作成する。
	 * 
	 * @return バリデーターインスタンス
	 */
	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
		ReloadableResourceBundleMessageSource messagSource = new ReloadableResourceBundleMessageSource();
		messagSource.setBasename("classpath:properties/validation/validation");
		messagSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
		validatorFactoryBean.setValidationMessageSource(messagSource);
		return validatorFactoryBean;
	}

	/**
	 * メソッドバリデーション構成
	 * 
	 * @return メソッドバリデーション用のpost-processor
	 */
	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		MethodValidationPostProcessor methodPostProcessor = new MethodValidationPostProcessor();
		methodPostProcessor.setValidator(validator());
		return methodPostProcessor;
	}

	@Override
	public Validator getValidator() {
		return validator();
	}
}
