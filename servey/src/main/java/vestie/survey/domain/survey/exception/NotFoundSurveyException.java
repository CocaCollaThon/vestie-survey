package vestie.survey.domain.survey.exception;

public class NotFoundSurveyException extends RuntimeException{
    public NotFoundSurveyException() {
        super("설문 정보가 없습니다.");
    }
}
