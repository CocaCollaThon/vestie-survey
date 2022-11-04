package vestie.survey.domain.surveyquestion.choice;

/**
 * Created by ShinD on 2022/10/11.
 */

import static javax.persistence.CascadeType.*;
import static lombok.AccessLevel.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import vestie.survey.domain.surveyquestion.SurveyQuestion;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
@DiscriminatorValue("choice_question")
public class ChoiceQuestion extends SurveyQuestion {

	private boolean isMultiSelectable = false; // 다중 선택 가능 여부

	@OneToMany(mappedBy = "choiceQuestion", cascade = ALL, orphanRemoval = true)
	private List<ChoiceOption> choiceOptions = new ArrayList<>(); // 질문 옵션 리스트

	//== 생성자 ==//
	public ChoiceQuestion(String title, Long questionOrder, boolean isMultiSelectable) {
		super(title, questionOrder);
		this.isMultiSelectable = isMultiSelectable;
	}

	//== 연관관계 설정 메서드 ==//
	public void addAllChoiceOption(List<ChoiceOption> choiceOptions) {
		for (ChoiceOption choiceOption : choiceOptions) {
			choiceOption.confirmChoiceQuestion(this);
			this.choiceOptions.add(choiceOption);
		}
	}

	@Override
	public void checkFieldNotNull() {
		super.checkFieldNotNull();

		for (ChoiceOption choiceOption : choiceOptions) {
			choiceOption.checkFieldNotNull();
		}
	}
}
