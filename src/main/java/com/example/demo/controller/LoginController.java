package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.config.Constants;
import com.example.demo.config.SecurityConfig;

@Controller
public class LoginController {

	private static String LOGIN_PAGE = "/login";
	private static String LOGIN_SUCCESS_PAGE = "/success";
	private static String ERROR = "error";
	

	/**
	 * <pre>
	 * デフォルトのログインページを表示
	 * {@link SecurityConfig コンフィグクラス}のloginPageに設定したパス
	 * </pre>
	 * @param model
	 * @return login.html
	 */
	@GetMapping(Constants.LOGIN_PATH)
	public String showLoginPage(ModelMap model, @RequestParam(value = "error", required = false) boolean error) {
		//ログイン失敗時にメッセージを出す
		if(error) {
			model.put(ERROR, "認証に失敗しました。idとパスワードを再度ご確認ください。");
		}
		
		model.put(Constants.TITLE_KEY, Constants.LOGINPAGE_TITLE);
		
		return LOGIN_PAGE;
	}
	
	/**
	 * <pre>
	 * ログイン成功時
	 * {@link SecurityConfig コンフィグクラス}のdefaultSuccessUrlに設定したパス
	 * </pre>
	 * @param model
	 * @return success.html
	 */
	@GetMapping(Constants.LOGIN_SUCCESS_PATH)
	public String loginSuccess(ModelMap model) {
		
		model.put(Constants.TITLE_KEY, Constants.LOGIN_SUCCESS_TITLE);
		
		return LOGIN_SUCCESS_PAGE;
	}
	
}
