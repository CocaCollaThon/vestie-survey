package vestie.servey.domain.writtenserveyfield;

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
import vestie.servey.domain.base.BaseEntity;
import vestie.servey.domain.surveyfield.SurveyField;
import vestie.servey.domain.writtensurvey.entity.WrittenSurvey;

/**
 * Created by ShinD on 2022/10/12.
 */
@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
@Inheritance(strategy = JOINED)
@DiscriminatorColumn(name = "question_type") // 기본값이 DTYPE
@Table(name = "written_survey_field")
public abstract class WrittenSurveyField extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "survey_field_id")
	private SurveyField surveyField; // 대상 질문 양식

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "written_survey_id")
	private WrittenSurvey writtenSurvey; // 작성된 설문지
}
