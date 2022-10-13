package vestie.servey.domain.answer;

import static javax.persistence.InheritanceType.*;
import static lombok.AccessLevel.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import vestie.servey.domain.base.BaseEntity;
import vestie.servey.domain.writtensurvey.entity.WrittenSurvey;

/**
 * Created by ShinD on 2022/10/12.
 */
@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
@Inheritance(strategy = JOINED)
@DiscriminatorColumn(name = "question_type")
@Table(name = "answer")
public abstract class Answer extends BaseEntity {

	@Column(nullable = false)
	protected Long surveyQuestionId; // 대상 질문 양식 Id


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "written_survey_id")
	protected WrittenSurvey writtenSurvey; // 작성된 설문지




	//== 생성자 ==//
	public Answer(Long surveyQuestionId) {
		this.surveyQuestionId = surveyQuestionId;
	}


	//== 연관관계 편의 메서드 ==//
	public void confirmWrittenSurvey(WrittenSurvey writtenSurvey) {
		this.writtenSurvey = writtenSurvey;
	}
}
