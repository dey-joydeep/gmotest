package com.gmo.test.db.entity.common;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 作成日時と更新日時を管理するよう共通クラス。
 * 
 * @author Joydeep Dey
 */
@MappedSuperclass
public class CreateUpdateTSColumns extends CommonEntity {

	private static final long serialVersionUID = 2914715347902292825L;

	/** 作成日時 */
	@Column(name = "created_at", nullable = false)
	private Instant createdAt;

	/** 更新日時 */
	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;

	/**
	 * 作成日時を取得する
	 * 
	 * @return 作成日時
	 */
	public Instant getCreatedAt() {
		return createdAt;
	}

	/**
	 * 作成日時を設定する
	 * 
	 * @param createdAt 作成日時
	 */
	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * 更新日時を取得する
	 * 
	 * @return 更新日時
	 */
	public Instant getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * 更新日時を設定する
	 * 
	 * @param updatedAt 更新日時
	 */
	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}
}
