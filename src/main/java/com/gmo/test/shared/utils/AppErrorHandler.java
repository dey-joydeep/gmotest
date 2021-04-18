package com.gmo.test.shared.utils;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gmo.test.shared.bean.SharedBean;
import com.gmo.test.shared.constatnt.AppConstants;

/**
 * カスタムエラー加工する用クラス
 * 
 * @author Joydeep Dey
 */
@Controller
@ControllerAdvice
public class AppErrorHandler extends ResponseEntityExceptionHandler {

	private static final Logger log = LogManager.getLogger(AppErrorHandler.class);

	/**
	 * モデルクラス項目がバリデーションエラーで失敗する場合、 エラー内容を加工して返却中のモデルクラス設定する。
	 * 
	 * @param ex      例外インスタンス
	 * @param request Webリクエスト
	 * @return レスポンスエンティティ
	 */
	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleBadRequest(final ConstraintViolationException ex, final WebRequest request) {
		SharedBean bean = new SharedBean();
		Map<String, String> errMap = new HashMap<>();
		ex.getConstraintViolations().iterator().forEachRemaining(v -> {
			if (v.getMessage() == null && v.getMessage().isBlank())
				return;
			String porpertyName = v.getPropertyPath().toString();
			errMap.put(extractName(porpertyName), v.getMessage());
		});

		bean.setErrors(errMap);
		return new ResponseEntity<>(bean, new HttpHeaders(), HttpStatus.OK);
	}

	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return super.handleExceptionInternal(ex, null, headers, HttpStatus.OK, request);
	}

	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		Map<String, String> errMap = new HashMap<>();
		BindingResult result = ex.getBindingResult();
		SharedBean bean = (SharedBean) result.getTarget();
		result.getFieldErrors().forEach(e -> {
			errMap.put(e.getField(), e.getDefaultMessage());
		});
		bean.setErrors(errMap);
		return new ResponseEntity<>(bean, new HttpHeaders(), HttpStatus.OK);
	}

	/**
	 * @param ex
	 * @param request
	 * @return result
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Object> handleInternalError(final Exception ex, final WebRequest request) {
		String bodyOfResponse = "{error.common}";
		log.error(AppConstants.SYSTEM_ERROR_MESSAGE, ex);
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,
				request);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		log.info(ex.getRequestURL() + " が見つからない。", ex);
		return handleExceptionInternal(ex, ex.getLocalizedMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		final String bodyOfResponse = "{error.common}";
		log.debug(AppConstants.SYSTEM_ERROR_MESSAGE, ex);
		return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
	}

	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		SharedBean bean = new SharedBean();
		bean.setSuccess(false);
		bean.setMessage(body != null ? body.toString() : ex.getLocalizedMessage());
		return super.handleExceptionInternal(ex, bean, headers, status, request);
	}

	private static String extractName(String key) {
		return key.substring(key.lastIndexOf('.') + 1);
	}
}
