package com.gmo.test.modules.login.bean;

import javax.validation.constraints.NotEmpty;

import com.gmo.test.shared.bean.SharedBean;

/**
 * ログイン画面とサーバーを連携するためのモデルクラス。
 * 
 * @author Joydeep Dey
 */
public class LoginBean extends SharedBean {

	/** ログインID */
	@NotEmpty(message = "{error.required.loginId}")
	private String loginId;

	/** パスワード */
	@NotEmpty(message = "{error.required.password}")
	private String password;

	/**
	 * ログインIDを取得する
	 * 
	 * @return ログインID
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * ログインIDを設定する
	 * 
	 * @param loginId ログインID
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * パスワードを取得する
	 * 
	 * @return パスワード
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * パスワードを設定する
	 * 
	 * @param password パスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
