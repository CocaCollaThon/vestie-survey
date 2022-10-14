package vestie.survey.domain.writtensurvey.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vestie.survey.domain.writtensurvey.entity.WrittenSurvey;

/**
 * Created by ShinD on 2022/10/13.
 */
public interface WrittenSurveyRepository extends JpaRepository<WrittenSurvey, Long> {
}
