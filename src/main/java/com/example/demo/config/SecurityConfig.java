package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

/**
 * @author Takumi
 * ログイン画面、ログアウト画面などを定義
 *
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	//ログイン失敗時のハンドラークラス
	private final CustomAuthenticationFailureHandler failureHandler;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				//ログイン設定をする
				.formLogin(login -> login
						//ログイン認証を発火するパス（ボタンを押した時に情報を渡すパス）
						.loginProcessingUrl(Constants.LOGIN_CERTIFICATION)
						//ログインページのパス
						.loginPage(Constants.LOGIN_PATH)
						//ログイン成功時のパス
						.defaultSuccessUrl(Constants.LOGIN_SUCCESS_PATH)
						//ログイン失敗時のハンドラー
						.failureHandler(failureHandler))
				//ログアウト設定
				.logout(logout -> logout
						//ログアウトするパス
						.logoutRequestMatcher(new AntPathRequestMatcher(Constants.LOGOUT_PATH))
						//ログアウト後のパス
						.logoutSuccessUrl(Constants.LOGIN_PATH)
						// セッションを無効にする
						.invalidateHttpSession(true)
						//特定のクッキーを削除
						.deleteCookies(Constants.JSESSIONID)
				)
				
				//認証なしでの許可パス設定
				.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(new AntPathRequestMatcher(Constants.LOGIN_PATH)).permitAll()
				.requestMatchers(new AntPathRequestMatcher(Constants.LOGIN_CERTIFICATION)).permitAll()
				.requestMatchers(new AntPathRequestMatcher(Constants.CSS_PATH)).permitAll()
				.requestMatchers(new AntPathRequestMatcher(Constants.JS_PATH)).permitAll()
				.requestMatchers(new AntPathRequestMatcher(Constants.IMAGE_PATH)).permitAll()
				.requestMatchers(new AntPathRequestMatcher(Constants.H2_CONSOLE)).permitAll()
				.anyRequest().authenticated()
		);
		return http.build();
	}
	
	/**
	 * <pre>
	 * パスワードをハッシュ化する
	 * </pre>
	 * @return ハッシュ化されたパスワード
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
