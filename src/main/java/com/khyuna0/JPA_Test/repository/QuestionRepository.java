package com.khyuna0.JPA_Test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.khyuna0.JPA_Test.entity.Questiontbl;

public interface QuestionRepository extends JpaRepository<Questiontbl, Long>{ // DAO 클래스의 역할
	
	// delete 리포지토리 생성할 때는 @Transactional 어노테이션 필수
	@Transactional 
	public void deleteAllByQtitle(String qtitle); // 질문 제목으로 삭제
	
	// 최근 질문글이 가장 위로 오도록 정렬하고, 모든 레코드를 반환
	public List<Questiontbl> findAllByOrderByQdateDesc();
	
	// 두 조건이 일치하는 질문 글 조회하기
	public Questiontbl findAllByQnumAndQtitle(Long qnum, String qtitle);
	
	// 제목이 정확히 일치하는 질문글들을 조회
	public List<Questiontbl> findAllByQtitle(String qtitle);
	
	// 제목에 특정 문자가 존재하면 조회하기 -> like , 최근 글이 위로 오도록 정렬
	public List<Questiontbl> findAllByQtitleLikeOrderByQdateDesc(String keyword);
	
	
}
