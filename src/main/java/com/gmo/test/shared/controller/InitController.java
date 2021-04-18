package com.gmo.test.shared.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ログイン画面または、ホーム画面を表示するためのコントローラークラス
 * 
 * @author Joydeep Dey
 */
@Controller
public class InitController {

	private static final Logger log = LogManager.getLogger(InitController.class);

	/**
	 * ログインまたは、ホーム画面をロードする。
	 * 
	 * @param request
	 * @return 認証結果より対象画面のHTMLファイル名
	 */
	@RequestMapping("/")
	public String initApp(HttpServletRequest request) {
		boolean isValid = false;
		// リクエスト属性よりユーザー認証情報を取得する
		Object ob = request.getAttribute("user-validation");
		if (ob != null) {
			isValid = Boolean.parseBoolean(ob.toString());
		}

		// 認証済みの場合、ホームへ遷移する
		if (isValid) {
			log.info("Valid session found. Redirecting to home page.");
			return "home";
		}

		// 認証されていないの場合、ログインへ遷移する
		log.info("Valid session not found. Redirecting to login page.");
		return "login";
	}
}
