package com.khyuna0.JPA_Test.dto;

import java.time.LocalDateTime;

import com.khyuna0.JPA_Test.entity.Questiontbl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {
	
	private Long qnum;
	private String qtitle;
	private String qcontent;
	private LocalDateTime qdate;
	private LocalDateTime udate;
	
	// DTO 객체에 들어온 값으로 entity 객체를 만들어서 변환 ( DTO -> Entity )
	public Questiontbl getEntity() {  
		Questiontbl question = new Questiontbl();
		question.setQtitle(this.qtitle);
		question.setQcontent(this.qcontent);
		
		return question;
	}
}
