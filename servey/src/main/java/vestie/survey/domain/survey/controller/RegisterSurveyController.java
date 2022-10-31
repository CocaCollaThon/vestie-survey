package vestie.survey.domain.survey.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vestie.survey.domain.auth.AuthMember;
import vestie.survey.domain.survey.controller.request.SurveyRequest;
import vestie.survey.domain.survey.repository.SurveyRepository;
import vestie.survey.domain.survey.service.SurveyService;
import vestie.survey.global.web.argumentresolver.auth.Auth;

import java.net.URI;

import static org.springframework.web.util.UriComponentsBuilder.fromPath;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RegisterSurveyController {

    private final SurveyService surveyService;

    @PostMapping("/v1/survey")
    public ResponseEntity<Void> registerSurvey(@Auth AuthMember authMember, @RequestBody SurveyRequest surveyRequest){

        Long surveyId = surveyService.save(surveyRequest.toDto(authMember.getId()));

        URI uri = fromPath("/v1/survey/{surveyId}")
                .buildAndExpand(surveyId)
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
