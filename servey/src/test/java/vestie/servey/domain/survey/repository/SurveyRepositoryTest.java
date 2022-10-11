package vestie.servey.domain.survey.repository;

import static org.assertj.core.api.Assertions.*;
import static vestie.servey.fixture.SurveyFixture.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import vestie.servey.domain.survey.entity.Survey;
import vestie.servey.domain.surveyfield.entity.SurveyField;
import vestie.servey.fixture.SurveyFieldFixture;

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

		ArrayList<SurveyField> surveyFields = new ArrayList<>();
		for (String title : List.of("질문1", "질문2", "질문3")) {
			surveyFields.add(SurveyFieldFixture.surveyField(title));
		}
		survey.addAllSurveyField(surveyFields);

		//when
		surveyRepository.save(survey);
		em.flush();
		em.clear();

		//then
		Survey saved = surveyRepository.findById(1L).get();
		assertThat(saved.getSurveyFields().size()).isEqualTo(surveyFields.size());
	}
}