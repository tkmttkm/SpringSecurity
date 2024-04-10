package com.example.demo.service;

import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.JPARepository;
import com.example.demo.utils.CustomUserDetails;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

/**
 * @author Takumi
 * ログイン認証時にユーザーIDより認証を行うクラス
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final JPARepository repository;	
	private final PasswordEncoder passwordEncoder;
	
	/**
	 * <pre>
	 * ユーザーIDからパスワードをテーブルから取得し、
	 * ログイン認証を行う
	 * </pre>
	 */
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		if(StringUtils.isBlank(userId)) {
			throw new UsernameNotFoundException("社員番号を正しく入力してください。");
		}
		
		Integer userID = Integer.parseInt(userId);
		List<UserEntity> allData = repository.findAll();
		for(UserEntity data: allData) {
			if(Integer.compare(userID, data.getUserId()) == 0) {
				String encodedPassword = passwordEncoder.encode(data.getPassword());
				String auth = data.getUserAuth();
				return new CustomUserDetails(userId, encodedPassword, toGrantedAuthorityList(auth));
			}
		}
		
		throw new UsernameNotFoundException("社員番号の登録がありません。入力社員番号 : " + userId);
	}
	
	private List<GrantedAuthority> toGrantedAuthorityList(String auth_type) {
		return Collections.singletonList(new SimpleGrantedAuthority(auth_type));
	}

}
