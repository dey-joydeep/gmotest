package com.gmo.test.modules.signup.service;

import com.gmo.test.modules.signup.bean.SignupBean;

/**
 * 新規登録画面のサービスクラス
 * 
 * @author Joydeep Dey
 */
public interface SignupService {

	/**
	 * 新規登録処理を実施する
	 * 
	 * @param signupBean 新規登録画面のモデルクラス
	 */
	void execSignup(SignupBean signupBean);
}
