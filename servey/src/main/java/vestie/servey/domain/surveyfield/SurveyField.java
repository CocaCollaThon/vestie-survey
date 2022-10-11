package vestie.servey.domain.surveyfield;

/**
 * Created by ShinD on 2022/10/11.
 */

import static javax.persistence.InheritanceType.*;
import static lombok.AccessLevel.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import vestie.servey.domain.survey.entity.Survey;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
@Inheritance(strategy = JOINED)
@DiscriminatorColumn(name = "question_type") // 기본값이 DTYPE
@Table(name = "survey_field")
public abstract class SurveyField {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "survey_field_id")
	private Long id; // PK

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "survey_id")
	private Survey survey; // 설문지

	private String title; // 질문 내용


	//== 생성자 ==//
	public SurveyField(String title) {
		this.title = title;
	}

	//== 연관관계 메서드 ==//
	public void confirmSurvey(Survey survey) {
		this.survey = survey;
	}
}
