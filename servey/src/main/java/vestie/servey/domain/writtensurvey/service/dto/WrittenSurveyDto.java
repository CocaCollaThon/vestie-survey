package vestie.servey.domain.writtensurvey.service.dto;

import java.util.ArrayList;
import java.util.List;

import vestie.servey.domain.answer.Answer;
import vestie.servey.domain.writtensurvey.entity.WrittenSurvey;
import vestie.servey.domain.writtensurvey.entity.enums.Gender;

/**
 * Created by ShinD on 2022/10/13.
 */
public record WrittenSurveyDto(
	Long memberId, // 작성자 회원 Id
	int writerAge, // 작성자 나이
	Gender writerGender, // 작성자 성별

	Long surveyId, // 대상 설문지 양식 Id
	List<ChoiceQuestionAnswerDto> choiceQuestionAnswerDtos, // 답변한 선택 질문
	List<SubjectiveQuestionAnswerDto> subjectiveQuestionAnswerDtos // 답변한 주관식 질문
) {

	public WrittenSurvey toEntity() {

		// 작성된 설문 엔티티 생성
		WrittenSurvey writtenSurvey = WrittenSurvey
			.builder()
			.memberId(memberId)
			.surveyId(surveyId)
			.writerAge(writerAge)
			.writerGender(writerGender)
			.build();

		// 선택형 질문 엔티티로 매핑
		List<Answer> answers =
			new ArrayList<>(
				choiceQuestionAnswerDtos.stream()
					.map(ChoiceQuestionAnswerDto::toEntity).toList()
			);

		// 객관식 질문 엔티티로 매핑
		answers.addAll(
			subjectiveQuestionAnswerDtos.stream()
				.map(SubjectiveQuestionAnswerDto::toEntity).toList()
		);

		// 작성된 설문에 작성된 답변 세팅
		writtenSurvey.confirmWrittenSurveyQuestions(answers);

		return writtenSurvey;
	}
}