package vestie.servey.domain.surveyfield.choice;

/**
 * Created by ShinD on 2022/10/11.
 */
import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
@Table(name = "choice_option")
public class ChoiceOption {

	@Id @GeneratedValue(strategy = IDENTITY)
	@Column(name = "choice_option_id")
	private Long id;

	private String name; // 옵션 이름

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "survey_field_id")
	private ChoiceQuestion choiceQuestion; // 해당 옵션을 가진 질문 문항


	//== 생성자 ==//
	public ChoiceOption(String name) {
		this.name = name;
	}

	//== 연관관계 설정 메서드 ==//
	public void confirmChoiceQuestion(ChoiceQuestion choiceQuestion) {
		this.choiceQuestion = choiceQuestion;
	}
}
