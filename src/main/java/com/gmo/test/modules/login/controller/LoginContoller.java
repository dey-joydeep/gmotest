package com.gmo.test.modules.login.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gmo.test.modules.login.bean.LoginBean;
import com.gmo.test.modules.login.service.LoginService;
import com.gmo.test.shared.annotation.NoSessionCheck;
import com.gmo.test.shared.constatnt.AppConstants;

/**
 * ログイン画面のコントローラークラス
 * 
 * @author Joydeep Dey
 */
@Validated
@RestController
@NoSessionCheck
public class LoginContoller {

	@Autowired
	private LoginService loginService;

	/**
	 * ログイン処理を行う。
	 * 
	 * @param loginBean ログイン画面のモデルクラス
	 * @param request   HTTPリクエストインスタンス
	 * @return ユーザー認証結果
	 */
	@RequestMapping(value = "/login", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public LoginBean login(@Valid @RequestBody LoginBean loginBean, HttpServletRequest request) {

		// ログイン認証
		loginService.userAuthenticate(loginBean);

		// ログインが成功の場合、新規セッションを作成し、属性を設定する
		if (loginBean.isSuccess()) {
			HttpSession session = request.getSession(true);
			session.setAttribute(AppConstants.SESSION_ATTR_LOGIN_ID, loginBean.getLoginId());
		}
		return loginBean;
	}

	/**
	 * ログアウト処理を行う。
	 * 
	 * @param request HTTPリクエストインスタンス
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logout(HttpServletRequest request) {
		// 既存セッション情報取得する
		HttpSession session = request.getSession(false);
		// セッション情報がアルノ場合、属性を外し、セッションを無効とする
		if (session != null) {
			session.removeAttribute(AppConstants.SESSION_ATTR_LOGIN_ID);
			session.invalidate();
		}
	}
}
