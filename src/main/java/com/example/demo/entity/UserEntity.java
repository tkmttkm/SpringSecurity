package com.example.demo.entity;

import com.example.demo.config.Constants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * エンティティクラス。LOGIN_USERをマッピング
 * @author Takumi
 *
 */
@Entity
@Table(name="LOGIN_USER")
@Getter
@NoArgsConstructor
public class UserEntity {
	
	@Id
	@Column(name="USER_ID")
	public Integer UserId;
	
	@Column(name="PASSWORD")
	public String Password;
	
	@Column(name="FIRST_NAME")
	public String FirstName;
	
	@Column(name="LAST_NAME")
	public String LastName;
	
	@Column(name="BIRTH_DAY")
	public Integer BirthDay;
	
	@Column(name="AUTH")
	public boolean Auth;
	
	/**
	 * <pre>
	 * ユーザー認証用の権限をテーブルデータにより返すメソッド
	 * </pre>
	 * @return ユーザー認証用の権限
	 */
	public String getUserAuth() {
		if(this.Auth) {
			return Constants.AUTH_ADMIN;
		} else {
			return Constants.AUTH_USER;
		}
	}
}
