package vestie.survey.domain.survey.entity;

/**
 * Created by ShinD on 2022/10/11.
 */

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vestie.survey.domain.answer.Answer;
import vestie.survey.domain.base.BaseEntity;
import vestie.survey.domain.survey.entity.value.Constraint;
import vestie.survey.domain.survey.entity.value.ExpectedTime;
import vestie.survey.domain.surveyquestion.SurveyQuestion;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "survey")
public class Survey extends BaseEntity {

	@Column(name = "member_id", nullable = false)
	private Long memberId; // 등록자 회원 Id(PK)

	@Column(name = "title", nullable = false)
	private String title; // 설문 제목

	@Column(name = "start_date", nullable = false)
	private LocalDate startDate; // 설문 시작일

	@Column(name = "end_date", nullable = false)
	private LocalDate endDate; // 설문 종료일

	@Column(name = "expected_time", nullable = false)
	private ExpectedTime expectedTime; // 예상 소요시간

	@Embedded
	private Constraint constraint; // 성별, 나이 제약조건

	/* TODO 나중에 deleteInBatch 쓰려면 orphanRemoval 안 쓸거 같아서, 그때 보고 수정 */
	@OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SurveyQuestion> surveyQuestions = new ArrayList<>(); // 필드 질문 항목들

	@Builder
	public Survey(Long memberId, String title, LocalDate startDate, LocalDate endDate, ExpectedTime expectedTime,
		Constraint constraint) {
		this.memberId = memberId;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.expectedTime = expectedTime;
		this.constraint = constraint;
	}

	public void addAllSurveyField(List<SurveyQuestion> surveyQuestions) {
		for (SurveyQuestion surveyQuestion : surveyQuestions) {
			surveyQuestion.confirmSurvey(this);
			this.surveyQuestions.add(surveyQuestion);
		}
	}

	@Override
	public void checkFieldNotNull() {
		checkNotNull(memberId, "회원 ID가 세팅되지 않았습니다.");
		checkNotNull(title, "회원 ID가 세팅되지 않았습니다.");
		checkNotNull(startDate, "회원 ID가 세팅되지 않았습니다.");
		checkNotNull(endDate, "회원 ID가 세팅되지 않았습니다.");
		checkNotNull(expectedTime, "회원 ID가 세팅되지 않았습니다.");

		for (SurveyQuestion question : surveyQuestions) {
			question.checkFieldNotNull();
		}
	}
}
