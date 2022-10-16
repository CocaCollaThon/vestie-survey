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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RegisterSurveyController {

    private final SurveyService surveyService;

    @PostMapping("/v1/survey")
    public ResponseEntity<Long> registerSurvey(@Auth AuthMember authMember, @RequestBody SurveyRequest surveyRequest){
        System.out.println(surveyRequest.getExpectedTime());
        System.out.println(surveyRequest.getGenderConstraint());
        System.out.println(surveyRequest.getTitle());
        System.out.println(surveyRequest.getStartDate().toString() + surveyRequest.getEndDate().toString());
        var savedId = surveyService.save(surveyRequest.toDto());
        return ResponseEntity.ok(savedId);
    }
}
