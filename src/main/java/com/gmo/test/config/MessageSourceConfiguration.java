package com.gmo.test.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;

/**
 * 動的なメッセージを表示するための構成クラス
 * 
 * @author Joydeep Dey
 */
@Configuration
public class MessageSourceConfiguration {

	private static final String PROPERTIES_PATH_ERROR = "properties/error/error.properties";

	/**
	 * メッセージのリソースを設定する
	 * 
	 * @return メッセージソース
	 * @throws IOException リソースファイルが見つからない場合
	 */
	@Bean
	@Primary
	public MessageSource messageSource() throws IOException {

		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		String baseName = new ClassPathResource(PROPERTIES_PATH_ERROR).getURI().toString();
		if (baseName.endsWith(".properties")) {
			baseName = baseName.replace(".properties", "");
		}
		messageSource.setBasename(baseName);
		messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
		messageSource.setCacheSeconds(60);
		return messageSource;
	}
}
