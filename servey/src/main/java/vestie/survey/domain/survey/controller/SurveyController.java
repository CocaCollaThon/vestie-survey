package vestie.survey.domain.survey.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vestie.survey.domain.auth.AuthMember;
import vestie.survey.domain.survey.controller.request.SurveyRequest;
import vestie.survey.domain.survey.controller.response.SurveyCompleteInfoResponse;
import vestie.survey.domain.survey.controller.response.SurveySimpleResponse;
import vestie.survey.domain.survey.service.SurveyQueryService;
import vestie.survey.domain.survey.service.SurveyService;
import vestie.survey.global.web.argumentresolver.auth.Auth;

import java.net.URI;
import java.util.List;

import static org.springframework.web.util.UriComponentsBuilder.fromPath;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/survey")
public class SurveyController {

    private final SurveyService surveyService;
    private final SurveyQueryService surveyQueryService;

    @PostMapping
    public ResponseEntity<Void> registerSurvey(@Auth AuthMember authMember, @RequestBody SurveyRequest surveyRequest){

        Long surveyId = surveyService.save(surveyRequest.toDto(authMember.getId()));

        URI uri = fromPath("/v1/survey/{surveyId}")
                .buildAndExpand(surveyId)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{surveyId}")
    public ResponseEntity<SurveyCompleteInfoResponse> getSurveyCompleteInfoById(
            @PathVariable(name = "surveyId") Long surveyId
    ) {
        SurveyCompleteInfoResponse surveyInfo
                = surveyQueryService.getSurveyCompleteInfoById(surveyId);

        return ResponseEntity.ok(surveyInfo);
    }

    @GetMapping
    public ResponseEntity<List<SurveySimpleResponse>> getAllSimpleInfo() {
        List<SurveySimpleResponse> simpleResponses = surveyQueryService.getAllSimpleInfo();
        return ResponseEntity.ok(simpleResponses);
    }
}
