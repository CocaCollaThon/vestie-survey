package vestie.servey.domain.survey.entity;

import static javax.persistence.EnumType.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;

import vestie.servey.domain.survey.entity.enums.GenderConstraint;

/**
 * Created by ShinD on 2022/10/11.
 */
@Embeddable
public class Constraint {

	@Enumerated(STRING)
	@Column(name = "gender_constraint")
	private GenderConstraint genderConstraint; // 성별 제약조건 [ONLY_MAN, ONLY_WOMAN, NO_CONSTRAINT]

	@Column(name = "min_age_constraint")
	private int minAgeConstraint = 0; // 최소 나이 제약조건

	@Column(name = "max_age_constraint")
	private int maxAgeConstraint = 500; // 최대 나이 제약조건
}
