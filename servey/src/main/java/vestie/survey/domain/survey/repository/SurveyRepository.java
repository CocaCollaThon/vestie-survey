package vestie.survey.domain.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vestie.survey.domain.survey.entity.Survey;

/**
 * Created by ShinD on 2022/10/11.
 */
public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
