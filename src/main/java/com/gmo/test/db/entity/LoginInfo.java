package com.gmo.test.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gmo.test.db.entity.common.CreateUpdateTSColumns;

/**
 * ログイン情報テーブルのエンティティクラス。
 * 
 * @author Joydeep Dey
 */
@Entity
@Table(name = "login_info")
public class LoginInfo extends CreateUpdateTSColumns {

	private static final long serialVersionUID = -539034194487975787L;

	/** ログインID */
	@Id
	@Column(name = "login_id")
	private String loginId;

	/** パスワード */
	@Column(name = "password")
	private String password;

	/** 削除フラグ */
	@Column(name = "delete_flg")
	private short deleteFlg;

	/**
	 * ログインIDを取得する
	 * 
	 * @return ログインID
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * ログインID設定する
	 * 
	 * @param loginId ログインID
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * パスワード を取得する
	 * 
	 * @return パスワード
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * パスワード設定する
	 * 
	 * @param password パスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 削除フラグを取得する
	 * 
	 * @return 削除フラグ
	 */
	public short getDeleteFlg() {
		return deleteFlg;
	}

	/**
	 * 削除フラグ設定する
	 * 
	 * @param deleteFlg 削除フラグ
	 */
	public void setDeleteFlg(short deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

}
