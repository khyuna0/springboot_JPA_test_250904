package com.khyuna0.JPA_Test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khyuna0.JPA_Test.entity.Questiontbl;
import com.khyuna0.JPA_Test.repository.QuestionRepository;

@SpringBootTest
public class TestQuestion {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Test
	@DisplayName("질문 등록 테스트")
	public void writeQuestion() {
		Questiontbl question = new Questiontbl();
		question.setQtitle("두번째 질문입니다.");
		question.setQcontent("내일은 금요일인가요?");
		
		questionRepository.save(question); // insert 문 쿼리 실행됨
	}
	
}
