package com.gmo.test.db.dao;

import com.gmo.test.db.entity.LoginInfo;

/**
 * ログイン情報テーブルに操作を行うためのDAOクラス
 * 
 * @author Joydeep Dey
 */
public interface LoginInfoDao {

	/**
	 * ログインIDでログイン情報を取得する。
	 * 
	 * @param loginId ログインID
	 * @return ログイン情報
	 */
	LoginInfo getLoginInfo(String loginId);

	/**
	 * 渡したログイン情報で新規レコードを作成する。
	 * 
	 * @param loginInfo ログイン情報
	 */
	void createNewUser(LoginInfo loginInfo);
}
