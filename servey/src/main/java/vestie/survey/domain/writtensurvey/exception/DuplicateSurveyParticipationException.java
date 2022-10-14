package vestie.survey.domain.writtensurvey.exception;

/**
 * Created by ShinD on 2022/10/14.
 */
public class DuplicateSurveyParticipationException extends RuntimeException {
	public DuplicateSurveyParticipationException() {
		super("회원님은 이미 해당 설문에 참여하셨습니다.");
	}
}
