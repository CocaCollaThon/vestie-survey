package vestie.servey.domain.writtenserveyfield.subjective;

import static lombok.AccessLevel.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import vestie.servey.domain.writtenserveyfield.WrittenSurveyField;

/**
 * Created by ShinD on 2022/10/12.
 */
@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
@DiscriminatorValue("subjective_question")
public class WrittenSubjectiveQuestion extends WrittenSurveyField {

	private String answer; // 답변 내용
}
