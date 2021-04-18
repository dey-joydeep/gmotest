package com.gmo.test.shared.constatnt;

/**
 * 共通定数クラス
 * 
 * @author Joydeep Dey
 *
 */
public class AppConstants {

	/** セッション属性： ログインID */
	public static final String SESSION_ATTR_LOGIN_ID = "login-id";

	/** リクエストヘッダ： IP */
	public static final String[] REQ_HEADER_IP = { "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
			"HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP",
			"HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR" };

	/** システムエラーメッセージ */
	public static final String SYSTEM_ERROR_MESSAGE = "システムエラーが発生しました。";
}
