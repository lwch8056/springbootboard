package com.wclee.sbb.answer;

import java.time.LocalDateTime;

import com.wclee.sbb.question.Question;
import com.wclee.sbb.user.SiteUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	
	@Column(columnDefinition = "TEXT")
	private String content; 
	
	private LocalDateTime createDate; 
	
	private LocalDateTime modifyDate; 	
	
	@ManyToOne
	private Question question; 
	
	@ManyToOne
	private SiteUser author; 
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
}
