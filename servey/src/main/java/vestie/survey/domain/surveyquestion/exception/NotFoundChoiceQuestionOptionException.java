package vestie.survey.domain.surveyquestion.exception;

public class NotFoundChoiceQuestionOptionException extends RuntimeException{
    public NotFoundChoiceQuestionOptionException() {
        super("선택형 질문 옵션 정보가 없습니다.");
    }
}
