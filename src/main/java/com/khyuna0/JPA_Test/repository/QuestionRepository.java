package com.khyuna0.JPA_Test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khyuna0.JPA_Test.entity.Questiontbl;

public interface QuestionRepository extends JpaRepository<Questiontbl, Long>{ // DAO 클래스의 역할
	
	
	
}
