package com.gmo.test.db.dao;

import java.time.Instant;

import org.springframework.stereotype.Repository;

import com.gmo.test.db.entity.LoginInfo;

/**
 * {@link LoginInfoDao}の実施クラス
 * 
 * @author Joydeep Dey
 */
@Repository
public class LoginInfoDaoImpl extends CommonDao implements LoginInfoDao {

	private static final long serialVersionUID = -9068050194824151040L;

	/**
	 * コンストラクタ
	 */
	public LoginInfoDaoImpl() {
		setClass(LoginInfo.class);
	}

	public LoginInfo getLoginInfo(String loginId) {
		return (LoginInfo) findById(loginId);
	}

	public void createNewUser(LoginInfo loginInfo) {
		// 共通情報設定する
		Instant currDt = Instant.now();
		loginInfo.setCreatedAt(currDt);
		loginInfo.setUpdatedAt(currDt);
		// 登録する
		create(loginInfo);
	}
}
