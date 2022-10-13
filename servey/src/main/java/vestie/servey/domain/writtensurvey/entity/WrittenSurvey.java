package vestie.servey.domain.writtensurvey.entity;

/**
 * Created by ShinD on 2022/10/12.
 */

import static javax.persistence.CascadeType.*;
import static lombok.AccessLevel.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vestie.servey.domain.answer.Answer;
import vestie.servey.domain.base.BaseEntity;
import vestie.servey.domain.writtensurvey.entity.enums.Gender;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
@Table(name = "written_survey")
public class WrittenSurvey extends BaseEntity {

	@Column(nullable = false)
	private Long memberId; // 작성자 아이디
	private int writerAge; // 작성자 나이
	private Gender writerGender; // 작성자 성별 [MAN, WOMAN]

	@Column(nullable = false)
	private Long surveyId; // 대상 설문 양식 ID

	@OneToMany(mappedBy = "writtenSurvey", orphanRemoval = true, cascade = ALL)
	private List<Answer> answers = new ArrayList<>(); // 작성된 설문 필드들







	//== 생성자 ==//
	@Builder
	public WrittenSurvey(Long memberId, int writerAge, Gender writerGender, Long surveyId) {
		this.memberId = memberId;
		this.writerAge = writerAge;
		this.writerGender = writerGender;
		this.surveyId = surveyId;
	}





	//== 답변에 대한 연관관계 세팅 ==//
	public void confirmWrittenSurveyQuestions(List<Answer> answers) {
		this.answers = answers;
		for (Answer answer : answers) {
			answer.confirmWrittenSurvey(this);
		}
	}






	@Override
	public void checkFieldNotNull() {
		checkNotNull(surveyId, "대상 설문 ID가 세팅되지 않았습니다.");
		checkNotNull(memberId, "회원 ID가 세팅되지 않았습니다.");

		for (Answer answer : answers) {
			answer.checkFieldNotNull();
		}
	}
}
