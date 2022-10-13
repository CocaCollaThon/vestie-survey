package vestie.servey.domain.answer.choice;

import static javax.persistence.CascadeType.*;
import static lombok.AccessLevel.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import vestie.servey.domain.answer.Answer;

/**
 * Created by ShinD on 2022/10/12.
 */
@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
@DiscriminatorValue("choice_question")
public class ChoiceQuestionAnswer extends Answer {

	@OneToMany(mappedBy = "choiceQuestionAnswer", orphanRemoval = true, cascade = ALL)
	private List<SelectedChoiceOption> selectedChoiceOptions = new ArrayList<>(); // 선택된 옵션들




	//== 생성자 ==//
	public ChoiceQuestionAnswer(Long surveyQuestionId) {
		super(surveyQuestionId);
	}




	// 연관관계 세팅
	public void confirmSelectedChoiceOptions(List<Long> selectedChoiceOptionIds) {
		this.selectedChoiceOptions.clear();

		for (Long selectedChoiceOptionId : selectedChoiceOptionIds) {
			SelectedChoiceOption selectedChoiceOption = new SelectedChoiceOption(selectedChoiceOptionId);
			selectedChoiceOption.confirmWrittenQuestion(this);
			this.selectedChoiceOptions.add(selectedChoiceOption);
		}
	}




	@Override
	public void checkFieldNotNull() {
		checkNotNull(this.surveyQuestionId, "답변의 대상이 되는 질문 ID가 세팅되지 않았습니다.");
		checkNotNull(this.writtenSurvey, "질문에 대한 답변을 포함한 전체 답변이 세팅되지 않았습니다.");

		for (SelectedChoiceOption option : selectedChoiceOptions) {
			option.checkFieldNotNull();
		}
	}
}
