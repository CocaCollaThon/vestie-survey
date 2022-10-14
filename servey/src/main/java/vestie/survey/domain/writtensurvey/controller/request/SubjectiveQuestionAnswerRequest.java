package vestie.survey.domain.writtensurvey.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import vestie.survey.domain.writtensurvey.service.dto.SubjectiveQuestionAnswerDto;

/**
 * Created by ShinD on 2022/10/13.
 */
public record SubjectiveQuestionAnswerRequest(
	@NotNull
	Long surveyQuestionId, // 대상 질문 Id

	@NotBlank
	String answer // 작성된 답변
) {
	public SubjectiveQuestionAnswerDto toServiceDto() {
		return new SubjectiveQuestionAnswerDto(surveyQuestionId, answer);
	}
}
