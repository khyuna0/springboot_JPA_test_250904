package com.khyuna0.JPA_Test.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // 엔티티 클래스로 설정함
@Table (name = "jpaquestiontbl") // 실제로 매핑될 데이터베이스의 테이블 이름 설정, 생략하면 클래스 이름으로 설정됨
@SequenceGenerator (
		name= "QUESTION_SEQ_GENERATOR", // JPA 내부 시퀀스 이름
		sequenceName ="QUESTION_SEQ", // 실제 DB에 있는 시퀀스 이름 
		initialValue = 1, // 시퀀스의 시작 값
		allocationSize = 1 // 시퀀스의 증가치 (생략하면 1)
		)

@Data // Entity 겸 DTO로 쓸 수 있음
@AllArgsConstructor
@NoArgsConstructor
public class Questiontbl {
	
	@Id // 해당 필드를 PK로 명시
	@Column (name = "qnum" ) // name = 실제로 DB 테이블에 만들어질 필드 이름 작성
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QUESTION_SEQ_GENERATOR") //
	private Long qnum; // 질문 번호, PK, 시퀀스로 자동증가 (시퀀스 미리 생성, 테이블 어노테이션 바로 밑에서 설정)
	
	@Column (name = "qtitle", length = 20, nullable = false )
	private String qtitle; // 질문 제목
	
	@Column (name = "qcontent", length = 200, nullable = false)
	private String qcontent; // 질문 내용
	
	@CreationTimestamp // 자동으로 입력(sysdate)
	private LocalDateTime qdate; // 최초 질문 등록일-sysdate 
	
	@UpdateTimestamp // 시간 갱신
	private LocalDateTime udate; // 해당 레코드가(질문글) 수정된 시간 자동 입력
}
