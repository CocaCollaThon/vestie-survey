package vestie.survey.domain.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vestie.survey.domain.survey.entity.Survey;

import java.time.LocalDate;
import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    /**
     * 현재 날짜 기준 종료된 설문 조회
     */
    List<Survey> findByEndDateLessThan(LocalDate now);
}
