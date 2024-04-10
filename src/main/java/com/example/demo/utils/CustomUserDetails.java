package com.example.demo.utils;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * ログイン認証をするクラス
 * @author Takumi
 *
 */
public class CustomUserDetails extends User {

	public CustomUserDetails(String user_no, String password, Collection<? extends GrantedAuthority> authorities) {
		super(user_no, password, authorities);
	}
	
}
