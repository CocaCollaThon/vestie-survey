package vestie.servey.domain.survey.entity.value;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.NoArgsConstructor;

/**
 * Created by ShinD on 2022/10/11.
 */
@Embeddable
@NoArgsConstructor
public class ExpectedTime {

	@Column(name = "expected_time", nullable = false)
	private int minute; // 분

	public ExpectedTime(int minute) {

		if (minute <= 0 ) {
			throw new RuntimeException("시간은 0분 이상어이야 합니다.");
		}

		this.minute = minute;
	}
}
