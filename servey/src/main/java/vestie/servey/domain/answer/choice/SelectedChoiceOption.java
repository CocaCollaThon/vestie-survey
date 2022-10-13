package vestie.servey.domain.answer.choice;

/**
 * Created by ShinD on 2022/10/12.
 */

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import vestie.servey.domain.base.BaseEntity;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
@Table(name = "selected_choice_option")
public class SelectedChoiceOption extends BaseEntity {

	@Column(nullable = false)
	private Long choiceOptionId; // 선택한 옵션 양식 ID

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "written_survey_field_id")
	private ChoiceQuestionAnswer choiceQuestionAnswer; // 작성된 답변





	//== 생성자 ==//
	public SelectedChoiceOption(Long choiceOptionId) {
		this.choiceOptionId = choiceOptionId;
	}





	// 연관관계 세팅
	public void confirmWrittenQuestion(ChoiceQuestionAnswer choiceQuestionAnswer) {
		this.choiceQuestionAnswer = choiceQuestionAnswer;
	}




	@Override
	public void checkFieldNotNull() {
		checkNotNull(this.choiceOptionId, "선택한 (객관식) 옵션의 대상 옵션 양식 ID가 세팅되지 않았습니다.");
		checkNotNull(this.choiceQuestionAnswer, "선택된 옵션이 어느 답변의 응답인지 세팅되지 않았습니다.");
	}
}
