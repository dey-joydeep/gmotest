package com.gmo.test.modules.login.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmo.test.db.dao.LoginInfoDao;
import com.gmo.test.db.entity.LoginInfo;
import com.gmo.test.modules.login.bean.LoginBean;
import com.gmo.test.shared.utils.CryptoUtil;

/**
 * ログインサービスの実装クラス
 * 
 * @author Joydeep Dey
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginInfoDao loginInfoDao;

	private static final Logger log = LogManager.getLogger(LoginServiceImpl.class);

	@Transactional(rollbackFor = Exception.class)
	public void userAuthenticate(LoginBean loginBean) {
		String loginId = loginBean.getLoginId();

		try {
			LoginInfo loginInfo = loginInfoDao.getLoginInfo(loginId);
			String hashedPassword = CryptoUtil.createMD5Hash(loginBean.getPassword());
			// ログインIDとパスワードでDB登録済みのデータを取得する。
			// 取得できない場合、エラーとして画面に結果を返却する。
			if (loginInfo == null || !loginInfo.getPassword().equals(hashedPassword)) {
				loginBean.setMessage("{error.invalid.credential}");
				return;
			}
			loginBean.setSuccess(true);
		} catch (Exception e) {
			loginBean.setSuccess(false);
			loginBean.setMessage("{error.common}");
			String message = "ログインする際にシステムエラーが発生しました。 ログインID: " + loginId;
			log.error(message, e);
		}
	}
}
