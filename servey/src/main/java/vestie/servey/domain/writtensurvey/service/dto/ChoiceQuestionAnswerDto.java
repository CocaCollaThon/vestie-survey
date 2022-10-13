package vestie.servey.domain.writtensurvey.service.dto;

import java.util.List;

import vestie.servey.domain.answer.Answer;
import vestie.servey.domain.answer.choice.ChoiceQuestionAnswer;

/**
 * Created by ShinD on 2022/10/13.
 */
public record ChoiceQuestionAnswerDto(
	Long surveyQuestionId, // 대상 질문 Id
	List<Long> choiceOptionIdList // 선택된 옵션 Id 목록
) {

	public Answer toEntity() {

		// 엔티티 생성
		ChoiceQuestionAnswer choiceQuestionAnswer = new ChoiceQuestionAnswer(surveyQuestionId);

		// 선택 옵션 세팅
		choiceQuestionAnswer.confirmSelectedChoiceOptions(this.choiceOptionIdList);

		return choiceQuestionAnswer;
	}
}
