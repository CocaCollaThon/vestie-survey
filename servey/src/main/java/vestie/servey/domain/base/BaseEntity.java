package vestie.servey.domain.base;

import static javax.persistence.GenerationType.*;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

/**
 * Created by ShinD on 2022/10/12.
 */
@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private Long id;

	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdDate;

	@LastModifiedDate
	private LocalDateTime modifiedDate;

	/**
	 * 반드시 채워져야 할 필드가 채워지지 않았는지에 대해 검사하는 메서드
	 * 엔티티마다 구현해 주어야 한다.
	 */
	public void checkFieldNotNull() {
		throw new RuntimeException("필드 검증 메서드가 구현되지 않았습니다");
	}



	// null 검사할 때 사용
	protected void checkNotNull(Object object, String message) {
		if (object == null) throw new RuntimeException(message);
	}
}
