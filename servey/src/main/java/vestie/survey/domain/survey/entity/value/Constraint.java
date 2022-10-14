package vestie.survey.domain.survey.entity.value;

import static javax.persistence.EnumType.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import vestie.survey.domain.survey.entity.enums.GenderConstraint;

/**
 * Created by ShinD on 2022/10/11.
 */
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Constraint {

	@Enumerated(STRING)
	@Column(name = "gender_constraint")
	private GenderConstraint genderConstraint; // 성별 제약조건 [ONLY_MAN, ONLY_WOMAN, NO_CONSTRAINT]

	@Builder.Default
	@Column(name = "min_age_constraint")
	private int minAgeConstraint = 0; // 최소 나이 제약조건

	@Builder.Default
	@Column(name = "max_age_constraint")
	private int maxAgeConstraint = 500; // 최대 나이 제약조건
}
