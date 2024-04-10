package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserEntity;

/**
 * Spring Data JPA を使用してDBから情報取得するクラス
 * @author Takumi
 *
 */
public interface JPARepository extends JpaRepository<UserEntity, Integer>{}