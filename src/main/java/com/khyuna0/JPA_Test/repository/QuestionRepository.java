package com.khyuna0.JPA_Test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.khyuna0.JPA_Test.entity.Questiontbl;

public interface QuestionRepository extends JpaRepository<Questiontbl, Long>{ // DAO 클래스의 역할
	
	// delete 리포지토리 생성할 때는 @Transactional 어노테이션 필수
	@Transactional 
	public void deleteAllByQtitle(String qtitle); // 질문 제목으로 삭제
	
	// 최근 질문글이 가장 위로 오도록 정렬하고, 모든 레코드를 반환
	public List<Questiontbl> findAllByOrderByQdateDesc();
	
	// 두 조건이 일치하는 질문 글 조회하기
	public Questiontbl findByQnumAndQtitle(Long qnum, String qtitle);
	
	// 제목이 정확히 일치하는 질문글들 조회
	public List<Questiontbl> findAllByQtitle(String qtitle); // 글 n개 검색 -> list 타입으로 반환 
	
	// find, findAll 차이
	// findAll : 여러개가 반환되는 경우 무조건 list 타입으로, find : 하나의 엔티티로 받을수도, list 타입으로 받을수도 있음 
	// find - 하나의 엔티티 타입으로 지정했는데, 여러 개가 반환될 경우 에러 발생
	
	// 제목에 특정 문자가 존재하면 조회하기 -> like , 최근 글이 위로 오도록 정렬
	public List<Questiontbl> findAllByQtitleLikeOrderByQdateDesc(String keyword);
	
	// SQL문 직접 쓰는 방법 (JPA SQL 문 : JPQL )
	// 실제 테이블 이름이 아닌 엔티티 이름 작성 (Questiontbl)
	// * 안됨 : q, q.필드이름 사용하기
	
	// @param 어노테이션 사용해서 파라미터 명시
	@Query("SELECT q FROM Questiontbl q WHERE q.qnum = :qnum")
	public Questiontbl findQuestionByQnum(@Param("qnum") Long qnum);
	
	// 질문 제목에 특정 문자가 들어있는 질문 글 조회
	@Query("SELECT q FROM Questiontbl q WHERE q.qtitle LIKE %:qtitle%")
	public Questiontbl findQuestionByQtitle(@Param("qtitle") String qtitle);
	
	// 질문 글 번호가 특정 값보다 큰 질문 글만 조회
	@Query("SELECT q FROM Questiontbl q WHERE q.qnum >= :number")
	public Questiontbl findQuestionByQnumber(@Param("number") Long number);
	
	// Native SQL문(오리지널 SQL문) 쓰기
	// 오리지널 SQL 문은 진짜 테이블 이름 (DB 테이블 이름)
	// @param 어노테이션 사용해서 파라미터 명시 방법은 동일
	@Query(value = "SELECT * FROM jspquestiontbl WHERE qnum= :qnum", nativeQuery = true)
	public Questiontbl findQuestionNativeByQnum(@Param("qnum") Long qnum); 
	
	// 기타 JPA 문법
	
	// 해당 레코드의 존재 여부 확인
	public boolean existsByQnum(Long qnum); // qnum이 존재하는 번호면 T 반환
	
	// 질문 글 번호가 특정 값 이상인 질문들만 조회 ( GreaterThan : 초과 )
	public List<Questiontbl> findByQnumGreaterThanEqual(Long qnum);
	
	
}
