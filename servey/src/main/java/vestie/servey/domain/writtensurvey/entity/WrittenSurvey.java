package vestie.servey.domain.writtensurvey.entity;

/**
 * Created by ShinD on 2022/10/12.
 */

import static lombok.AccessLevel.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vestie.servey.domain.base.BaseEntity;
import vestie.servey.domain.survey.entity.Survey;
import vestie.servey.domain.writtensurvey.entity.enums.Gender;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
@Table(name = "written_survey")
public class WrittenSurvey extends BaseEntity {

	private Long memberId; // 작성자 아이디

	private int writerAge; // 작성자 나이
	private Gender writerGender; // 작성자 성별 [MAN, WOMAN]

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "survey_id")
	private Survey survey;

	//== 생성자 ==//
	@Builder
	public WrittenSurvey(Long memberId, int writerAge, Gender writerGender) {
		this.memberId = memberId;
		this.writerAge = writerAge;
		this.writerGender = writerGender;
	}

	//== 연관관계 설정 메서드 ==//
	public void confirmSurvey(Survey survey) {
		this.survey = survey;
	}
}
