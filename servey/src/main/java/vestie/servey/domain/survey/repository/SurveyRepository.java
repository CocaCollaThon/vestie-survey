package vestie.servey.domain.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vestie.servey.domain.survey.entity.Survey;

/**
 * Created by ShinD on 2022/10/11.
 */
public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
