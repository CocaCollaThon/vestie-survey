package vestie.survey.domain.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vestie.survey.domain.survey.entity.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
