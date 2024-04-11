package com.example.demo.config;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;

/**
 * ログイン認証失敗時のクラス
 * @author Takumi
 *
 */
@Component
@NoArgsConstructor
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	public CustomAuthenticationFailureHandler(String failureUrl) {
		super(failureUrl);
	}
	
	/**
	 * <pre>
	 * ログイン認証失敗時のメソッド
	 * {@link Constants#LOGIN_PATH}?error=trueにリダイレクトする
	 * <pre>
	 */
	@Override
	public void onAuthenticationFailure(
			HttpServletRequest request,
			HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
	
		//指定したURLにリダイレクト
		super.setDefaultFailureUrl(Constants.LOGIN_PATH + "?" + Constants.ERROR + "=true");
		super.onAuthenticationFailure(request, response, exception);
	}
	
}
