package com.gmo.test.shared.bean;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 共通モデル項目クラス
 * 
 * @author Joydeep Dey
 */
public class SharedBean {

	/** 処理結果フラグ */
	private boolean success;

	/** 一般的なメッセージ */
	private String message;

	/** バリデーションエラーメッセージ */
	private Map<String, String> errors;

	/** メッセージキーに差し替え用パラメータ配列 */
	@JsonIgnore
	private Object[] messageParams;

	/**
	 * 処理結果フラグ を取得する
	 * 
	 * @return 処理結果フラグ
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * 処理結果フラグ を設定する
	 * 
	 * @param success 処理結果フラグ
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * 一般的なメッセージを取得する
	 * 
	 * @return 一般的なメッセージ
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 一般的なメッセージを設定する
	 * 
	 * @param message 一般的なメッセージ
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * バリデーションエラーメッセージを取得する
	 * 
	 * @return バリデーションエラーメッセージ
	 */
	public Map<String, String> getErrors() {
		return errors;
	}

	/**
	 * バリデーションエラーメッセージを設定する
	 * 
	 * @param errors バリデーションエラーメッセージ
	 */
	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	/**
	 * メッセージキーに差し替え用パラメータ配列を取得する
	 * 
	 * @return メッセージキーに差し替え用パラメータ配列
	 */
	public Object[] getMessageParams() {
		return messageParams;
	}

	/**
	 * メッセージキーに差し替え用パラメータ配列を設定する
	 * 
	 * @param messageParams メッセージキーに差し替え用パラメータ配列
	 */
	public void setMessageParams(Object... messageParams) {
		this.messageParams = messageParams;
	}
}
