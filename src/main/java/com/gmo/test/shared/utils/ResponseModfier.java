package com.gmo.test.shared.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.gmo.test.shared.bean.SharedBean;

/**
 * レスポンスを画面に返却する前、エラーメッセージキーがある場合、該当のメッセージを取得して設定する。
 * 
 * @author Joydeep Dey
 */
@ControllerAdvice
public class ResponseModfier implements ResponseBodyAdvice<Object> {

	@Autowired
	private MessageSource messageSource;

	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {

		if (!(body instanceof SharedBean))
			return body;

		SharedBean bean = (SharedBean) body;
		String messageKey = bean.getMessage();
		Object[] params = bean.getMessageParams();
		if (messageKey != null && !messageKey.isBlank()) {
			messageKey = messageKey.trim();
			// メッセージキーがある場合
			if (messageKey.matches("^\\{.+\\}$")) {
				messageKey = messageKey.replaceAll("\\{|\\}", "").trim();
				// メッセージソースより該当のメッセージを取得する
				String message = messageSource.getMessage(messageKey, params, null);
				bean.setMessage(message);
			}
		}
		return body;
	}
}
