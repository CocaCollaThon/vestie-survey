package vestie.servey.domain.writtensurvey.service.dto;

import vestie.servey.domain.answer.Answer;
import vestie.servey.domain.answer.subjective.SubjectiveQuestionAnswer;

/**
 * Created by ShinD on 2022/10/13.
 */
public record SubjectiveQuestionAnswerDto(
	Long surveyQuestionId, // 대상 질문 Id
	String answer // 작성된 답변
) {
	public Answer toEntity() {
		return new SubjectiveQuestionAnswer(surveyQuestionId, this.answer);
	}
}
