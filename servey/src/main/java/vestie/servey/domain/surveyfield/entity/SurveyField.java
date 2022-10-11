package vestie.servey.domain.surveyfield.entity;

/**
 * Created by ShinD on 2022/10/11.
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vestie.servey.domain.survey.entity.Survey;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "survey_field")
public class SurveyField {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "survey_field_id")
	private Long id; // PK

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "survey_id")
	private Survey survey; // 설문지

	private String title; // 질문 내용

	public SurveyField(String title) {
		this.title = title;
	}

	public void confirmSurvey(Survey survey) {
		this.survey = survey;
	}
}
