package vestie.survey.domain.writtensurvey.controller.request;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import vestie.survey.domain.writtensurvey.service.dto.ChoiceQuestionAnswerDto;

/**
 * Created by ShinD on 2022/10/13.
 */
public record ChoiceQuestionAnswerRequest(
	@NotNull
	Long surveyQuestionId, // 대상 질문 Id
	@NotEmpty
	List<Long> choiceOptionIdList // 선택된 옵션 Id 목록 (최소한 하나는 있어야 함)
) {

	public ChoiceQuestionAnswerDto toServiceDto() {
		return new ChoiceQuestionAnswerDto(surveyQuestionId, choiceOptionIdList);
	}
}
