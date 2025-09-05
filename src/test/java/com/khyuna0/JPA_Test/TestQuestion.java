package com.khyuna0.JPA_Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khyuna0.JPA_Test.dto.QuestionDto;
import com.khyuna0.JPA_Test.entity.Questiontbl;
import com.khyuna0.JPA_Test.repository.QuestionRepository;

@SpringBootTest
public class TestQuestion {

	@Autowired
	private QuestionRepository questionRepository;
	
//	@Test
//	@DisplayName("질문 등록 테스트")
//	public void writeQuestion() {
//		Questiontbl question = new Questiontbl();
//		question.setQtitle("세번째 질문입니다.");
//		question.setQcontent("내일은 토요일인가요?");
		
//		QuestionDto questionDto = new QuestionDto();
//		questionDto.setQtitle("홍길동");
//		questionDto.setQcontent("홍길동");
//		
//		Questiontbl questiontbl = questionDto.getEntity(); // DTO 객체를 Entity 객체로 변환
//		
//		// JPA 메서드는 엔티티 객체만 인자값으로 받을 수 있다!!!!!
//		questionRepository.save(questiontbl); // insert 문 쿼리 실행
//		// save -> 테이블과 일치하는 엔티티 객체를 넣으면 초기화된 값으로 테이블 만들어줌
//		// 엔티티로 선언된 객체만 인수로 받을 수 있음 (일반 클래스 불가)
//		
//	}
	
//	@Test
//	@DisplayName("질문 삭제 테스트")
//	public void deleteQuestion() {
//		// .deleteAll(); -> 주의! 모든 데이터 삭제
//		//questionRepository.deleteById(3L); // 롱 타입은 숫자 + L
//		// qnum=3인 레코드 삭제
//		
//		// 엔티티 리스트 삭제 (원하는 레코드들만 삭제)
//		List<Questiontbl> qList = new ArrayList<>();
//		questionRepository.deleteAll(qList); 
//		
//		questionRepository.deleteAllByQtitle("홍길동"); // 질문 제목으로 찾아 삭제
//		
//	}
	
//	@Test
//	@DisplayName("조회 테스트")
//	public void searchQuestion() {
//		
//		// .findAll(); -> 모든 레코드 가져오기 ( = SELECT * FROM jpaquestiontbl) 
//		List<Questiontbl> qList = questionRepository.findAll();
//		
//		for (Questiontbl q : qList) {
//			System.out.println("질문번호 : " + q.getQnum());
//			System.out.println("제목 : " + q.getQtitle());
//			System.out.println("내용 : " + q.getQcontent());
//			System.out.println("질문 날짜 : " + q.getQdate());
//			System.out.println("질문 수정 날짜 : " + q.getUdate());
//			System.out.println("------------------------");
//		}
//		
//		// 가장 최근 질문이 위로 오도록 정렬해서 출력
//		
//		List<Questiontbl> qList2 = questionRepository.findAllByOrderByQdateDesc();
//		
//		for (Questiontbl q : qList2) {
//			System.out.println("질문번호 : " + q.getQnum());
//			System.out.println("제목 : " + q.getQtitle());
//			System.out.println("내용 : " + q.getQcontent());
//			System.out.println("질문 날짜 : " + q.getQdate());
//			System.out.println("질문 수정 날짜 : " + q.getUdate());
//			System.out.println("------------------------");
//		}
//		
//	}
	
	@Test
	@DisplayName("특정 질문 검색")
	public void searchQuestionByField() {
		Optional<Questiontbl> questionOption = questionRepository.findById(4L); //기본키로 검색
		// select * from jpaquestiontbl where qnum=4
		
		// id (PK) 로 찾을 때는 Optional 사용
		// 기본키로 검색했을 경우 레코드가 1개 또는 존재하지 않는 경우 발생
		// Option<Questiontbl> 형태로 반환 타입을 정해야 한다
		// isPresent() 를 활요하면 Option<Questiontbl> 내에 객체의 존재여부를 알수가 있다.
		if(questionOption.isPresent()) { //참이면 해당 기본키를 가진 레코드가 존재->조회 OK 
			Questiontbl question = questionOption.get(); //해당 기본키를 가진 엔티티(레코드)가 반환
			System.out.println(question.getQnum());
			System.out.println(question.getQtitle());
		} else { //해당 기본키를 가진 레코드가 존재 X->조회실패
			System.out.println("해당 번호의 질문은 존재하지 않습니다.");
		}
		
		// select * from jpaquestiontbl where qnum=1 and qtitle=홍길동
		Questiontbl question1 = questionRepository.findAllByQnumAndQtitle(4L, "홍길동");
		System.out.println("글번호가 8번이고 글제목이 홍길동인 레코드 :"+question1.getQnum());
		
		
		// 제목이 정확히 일치하는 조건으로 조회
		List<Questiontbl> questions = questionRepository.findAllByQtitle("홍길동");
		
		for(Questiontbl question: questions) {
			System.out.println(question.getQtitle());
		}
		
		// 질문 제목에 특정 문자가 들어 있으면 찾는 조건으로 조회 -> like
		List<Questiontbl> questionsLike = questionRepository.findAllByQtitleLikeOrderByQdateDesc("%질문%");
		
		for(Questiontbl question : questionsLike) {
			System.out.println(question.getQnum());
			System.out.println(question.getQtitle());
			
			System.out.println("--------------------------");
		}
	}
	
	
	
	
	
}
