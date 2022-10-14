package vestie.survey.domain.surveyquestion;

/**
 * Created by ShinD on 2022/10/11.
 */

import static javax.persistence.InheritanceType.*;
import static lombok.AccessLevel.*;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import vestie.survey.domain.base.BaseEntity;
import vestie.survey.domain.survey.entity.Survey;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
@Inheritance(strategy = JOINED)
@DiscriminatorColumn(name = "question_type") // 기본값이 DTYPE
@Table(name = "survey_question")
public abstract class SurveyQuestion extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "survey_id")
	private Survey survey; // 설문지

	private String title; // 질문 내용


	//== 생성자 ==//
	public SurveyQuestion(String title) {
		this.title = title;
	}

	//== 연관관계 메서드 ==//
	public void confirmSurvey(Survey survey) {
		this.survey = survey;
	}
}
