package vestie.survey.domain.answer.subjective;

import static lombok.AccessLevel.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import vestie.survey.domain.answer.Answer;

/**
 * Created by ShinD on 2022/10/12.
 */
@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
@DiscriminatorValue("subjective_question")
public class SubjectiveQuestionAnswer extends Answer {

	private String answer; // 답변 내용




	//== 생성자 ==//
	public SubjectiveQuestionAnswer(Long surveyQuestionId, String answer) {
		super(surveyQuestionId);
		this.answer = answer;
	}




	@Override
	public void checkFieldNotNull() {
		checkNotNull(this.surveyQuestionId, "답변의 대상이 되는 질문 ID가 세팅되지 않았습니다.");
		checkNotNull(this.writtenSurvey, "질문에 대한 답변을 포함한 전체 답변이 세팅되지 않았습니다.");
		checkNotNull(this.answer, "주관식 답변이 세팅되지 않았습니다.");
	}
}
