package com.gmo.test.db.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.gmo.test.db.entity.common.CommonEntity;

/**
 * データベース操作用の共通DAOクラス
 * 
 * @author Joydeep Dey
 */
@Repository
public class CommonDao implements Serializable {

	private static final long serialVersionUID = -749545283292765905L;

	/** JPAエンティティマネジャー */
	@PersistenceContext
	private EntityManager em;

	/** エンティティクラス */
	private Class<?> clazz;

	/**
	 * ResultSetを対象のエンティティをマッピングするためのセッター
	 * 
	 * @param clazz エンティティクラス
	 */
	protected final void setClass(final Class<?> clazz) {
		this.clazz = clazz;
	}

	/**
	 * 主キーでDB情報を取得する
	 * 
	 * @param id 主キー値
	 * @return 取得した結果。取得できない場合はNULLを返却する。
	 * 
	 */
	protected CommonEntity findById(final Object id) {
		Object entity = em.find(clazz, id);
		return entity == null ? null : (CommonEntity) em.find(clazz, id);
	}

	/**
	 * 新規レコードを登録する
	 * 
	 * @param entity 登録情報
	 */
	protected void create(final CommonEntity entity) {
		em.persist(entity);
	}
}
