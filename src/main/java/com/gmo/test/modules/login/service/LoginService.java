package com.gmo.test.modules.login.service;

import com.gmo.test.modules.login.bean.LoginBean;

/**
 * ログイン画面のサービスクラス
 * 
 * @author Joydeep Dey
 */
public interface LoginService {

	/**
	 * ログイン情報を認証する。
	 * 
	 * @param loginBean ログイン画面のモデルクラス
	 */
	void userAuthenticate(LoginBean loginBean);

}
