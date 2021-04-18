package com.gmo.test.modules.signup.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmo.test.db.dao.LoginInfoDao;
import com.gmo.test.db.entity.LoginInfo;
import com.gmo.test.modules.signup.bean.SignupBean;
import com.gmo.test.shared.utils.CryptoUtil;

/**
 * 新規登録画面のサービス実装クラス
 * 
 * @author Joydeep Dey
 */
@Service
public class SignupServiceImpl implements SignupService {

	@Autowired
	private LoginInfoDao loginInfoDao;

	private static final Logger log = LogManager.getLogger(SignupServiceImpl.class);

	@Transactional(rollbackFor = Exception.class)

	public void execSignup(SignupBean signupBean) {
		try {
			String loginId = signupBean.getLoginId();
			LoginInfo loginInfo = loginInfoDao.getLoginInfo(loginId);
			// 重複のログインIDをチェックする
			if (loginInfo == null) {
				loginInfo = new LoginInfo();
			} else {
				// 重複である場合、エラーとして画面に返却する。
				signupBean.setMessage("{error.duplicate.loginid}");
				return;
			}

			// 重複でない場合、新規登録する。
			loginInfo.setLoginId(loginId);
			String password = CryptoUtil.createMD5Hash(signupBean.getPassword());
			loginInfo.setPassword(password);
			loginInfoDao.createNewUser(loginInfo);
			signupBean.setSuccess(true);
		} catch (Exception e) {
			signupBean.setSuccess(false);
			signupBean.setMessage("{error.common}");
			log.error("新規ユーザー登録際にシステムエラーが発生しました。", e);
		}
	}
}
