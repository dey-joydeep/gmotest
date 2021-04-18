package com.gmo.test.modules.signup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.gmo.test.shared.annotation.NoSessionCheck;

/**
 * 新規登録画面に遷移するためのコントローラークラス
 * 
 * @author Joydeep Dey
 */
@Controller
@NoSessionCheck
public class SignupInitController {

	/**
	 * 新規登録画面をロードする
	 * 
	 * @return 新規登録画面HTMLファイル名
	 */
	@GetMapping(value = "/signup")
	public String initSignUp() {
		return "signup";
	}
}
