package vestie.survey.domain.surveyquestion.subjective;

import static javax.persistence.EnumType.*;
import static lombok.AccessLevel.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vestie.survey.domain.surveyquestion.SurveyQuestion;
import vestie.survey.domain.surveyquestion.subjective.enums.SubjectiveType;

/**
 * 주관식 질문
 * Created by ShinD on 2022/10/11.
 */
@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
@DiscriminatorValue("subjective_question")
public class SubjectiveQuestion extends SurveyQuestion {

	@Enumerated(STRING)
	private SubjectiveType subjectiveType; // 주관식 질문 유형, [SHORT_ANSWER, ESSAY]

	@Builder
	public SubjectiveQuestion(String title, SubjectiveType subjectiveType) {
		super(title);
		this.subjectiveType = subjectiveType;
	}
}
