package vestie.survey.domain.writtensurvey.controller.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import vestie.survey.domain.writtensurvey.entity.enums.Gender;
import vestie.survey.domain.writtensurvey.service.dto.ChoiceQuestionAnswerDto;
import vestie.survey.domain.writtensurvey.service.dto.SubjectiveQuestionAnswerDto;
import vestie.survey.domain.writtensurvey.service.dto.WrittenSurveyDto;

/**
 * Created by ShinD on 2022/10/13.
 */
public record WrittenSurveyRequest(

	@Range(min=1, max = 130)
	int writerAge, // 작성자 나이

	@NotNull
	Gender writerGender, // 작성자 성별 (MAN, WOMAN)

	@NotNull
	Long surveyId, // 대상 설문지 양식 Id

	@Valid
	List<ChoiceQuestionAnswerRequest> choiceQuestionAnswerRequests, // 답변한 선택 질문

	@Valid
	List<SubjectiveQuestionAnswerRequest> subjectiveQuestionAnswerRequests// 답변한 주관식 질문

) {
	public WrittenSurveyDto toServiceDto(Long memberId) {

		if (choiceQuestionAnswerRequests.isEmpty() && subjectiveQuestionAnswerRequests.isEmpty()) {
			throw new ValidationException("답변한 질문은 하나 이상이어야 합니다.");
		}

		// 답변 리스트 Service Dto 로 변환
		List<ChoiceQuestionAnswerDto> choiceAnswerDtos =
			choiceQuestionAnswerRequests.stream()
				.map(ChoiceQuestionAnswerRequest::toServiceDto)
				.toList();

		List<SubjectiveQuestionAnswerDto> subjectiveAnswerDtos =
			subjectiveQuestionAnswerRequests.stream()
				.map(SubjectiveQuestionAnswerRequest::toServiceDto)
				.toList();

		return WrittenSurveyDto.builder()
			.memberId(memberId)
			.writerAge(writerAge)
			.writerGender(writerGender)
			.surveyId(surveyId)
			.choiceQuestionAnswerDtos(choiceAnswerDtos)
			.subjectiveQuestionAnswerDtos(subjectiveAnswerDtos)
			.build();
	}
}