package vestie.survey.domain.survey.repository;

import static org.assertj.core.api.Assertions.*;
import static vestie.survey.fixture.SurveyFixture.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import vestie.survey.domain.survey.entity.Survey;
import vestie.survey.domain.surveyquestion.SurveyQuestion;
import vestie.survey.fixture.SurveyQuestionFixture;

/**
 * Created by ShinD on 2022/10/11.
 */
@DataJpaTest
class SurveyRepositoryTest {

	@Autowired
	private SurveyRepository surveyRepository;

	@Autowired
	private EntityManager em;

	@Test
	@DisplayName("CascadeType.ALL 동작 확인 테스트")
	public void addSurveyAndField() throws Exception {
		//given
		Survey survey = survey();

		ArrayList<SurveyQuestion> surveyFields = new ArrayList<>();

		for (int i =1; i <= 3; i++){
			surveyFields.add(SurveyQuestionFixture.surveyField("질문"+i, (long) i));
		}
		survey.addAllSurveyField(surveyFields);

		//when
		surveyRepository.save(survey);
		em.flush();
		em.clear();

		//then
		Survey saved = surveyRepository.findById(1L).get();
		assertThat(saved.getSurveyQuestions().size()).isEqualTo(surveyFields.size());
	}
}