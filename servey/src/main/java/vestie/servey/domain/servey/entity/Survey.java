package vestie.servey.domain.servey.entity;

/**
 * Created by ShinD on 2022/10/11.
 */

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "survey")
public class Survey {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "servey_id")
	private Long id; // PK

	@Column(name = "member_id", nullable = false)
	private Long memberId; // 등록자 회원 Id(PK)

	@Column(name = "title", nullable = false)
	private String title; // 설문 제목

	@Column(name = "start_date", nullable = false)
	private LocalDate startDate; // 설문 시작일

	@Column(name = "end_date", nullable = false)
	private LocalDate endDate; // 설문 종료일

	@Column(name = "expected_time", nullable = false)
	private int expectedTime; // 예상 소요시간

	@Embedded
	private Constraint constraint; // 성별, 나이 제약조건
}
