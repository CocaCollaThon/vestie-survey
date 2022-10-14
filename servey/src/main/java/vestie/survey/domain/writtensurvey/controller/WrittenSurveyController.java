package vestie.survey.domain.writtensurvey.controller;

import static org.springframework.web.util.UriComponentsBuilder.*;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vestie.survey.domain.auth.AuthMember;
import vestie.survey.domain.writtensurvey.controller.request.WrittenSurveyRequest;
import vestie.survey.domain.writtensurvey.service.WrittenSurveyService;
import vestie.survey.global.web.argumentresolver.auth.Auth;

/**
 * Created by ShinD on 2022/10/14.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WrittenSurveyController {

	private final WrittenSurveyService writtenSurveyService;


	@PostMapping("/v1/written-survey")
	public ResponseEntity<Void> respondSurvey(
		@Auth AuthMember authMember,
		@Valid @RequestBody WrittenSurveyRequest request
	) {

		// 설문에 대한 응답 저장
		Long writtenSurveyId =
			writtenSurveyService.save(request.toServiceDto(authMember.getId()));

		// 저장된 주소 반환
		URI uri = fromPath("/v1/written-survey/{writtenSurveyId}")
			.buildAndExpand(writtenSurveyId)
			.toUri();

		return ResponseEntity.created(uri).build();
	}
}
