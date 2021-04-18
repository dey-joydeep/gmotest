package com.gmo.test.modules.signup.controller;

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

import com.gmo.test.modules.signup.bean.SignupBean;
import com.gmo.test.modules.signup.service.SignupService;
import com.gmo.test.shared.annotation.NoSessionCheck;
import com.gmo.test.shared.constatnt.AppConstants;

/**
 * 新規登録画面のコントローラークラス
 * 
 * @author Joydeep Dey
 */
@Validated
@RestController
@NoSessionCheck
@RequestMapping("/signup")
public class SignupProcessController {

	@Autowired
	private SignupService signupService;

	/**
	 * 新規登録処理を行う。
	 * 
	 * @param signupBean 新規登録画面のモデルクラス
	 * @param request    HTTPリクエストインスタンス
	 * @return 新規登録結果
	 */
	@RequestMapping(value = "/complete", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public SignupBean signup(@Valid @RequestBody SignupBean signupBean, HttpServletRequest request) {

		// 新規登録処理
		signupService.execSignup(signupBean);

		// 新規登録が成功の場合、新規セッションを作成し、属性を設定する
		if (signupBean.isSuccess()) {
			HttpSession session = request.getSession(true);
			session.setAttribute(AppConstants.SESSION_ATTR_LOGIN_ID, signupBean.getLoginId());
		}
		return signupBean;
	}
}
