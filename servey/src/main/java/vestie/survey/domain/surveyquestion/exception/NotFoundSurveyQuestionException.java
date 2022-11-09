package vestie.survey.domain.surveyquestion.exception;

public class NotFoundSurveyQuestionException extends RuntimeException{
    public NotFoundSurveyQuestionException() {
        super("질문 정보가 없습니다.");
    }
}
