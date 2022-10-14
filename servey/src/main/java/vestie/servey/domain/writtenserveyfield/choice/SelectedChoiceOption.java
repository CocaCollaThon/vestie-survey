package vestie.servey.domain.writtenserveyfield.choice;

/**
 * Created by ShinD on 2022/10/12.
 */

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import vestie.servey.domain.base.BaseEntity;
import vestie.servey.domain.surveyfield.choice.ChoiceOption;
import vestie.servey.domain.writtenserveyfield.WrittenSurveyField;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
@Table(name = "selected_choice_option")
public class SelectedChoiceOption extends BaseEntity {

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "choice_option_id")
	private ChoiceOption choiceOption; // 선택한 옵션 양식

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "written_survey_field_id")
	private WrittenSurveyField writtenSurveyField; // 응답된 설문지 질문
}
