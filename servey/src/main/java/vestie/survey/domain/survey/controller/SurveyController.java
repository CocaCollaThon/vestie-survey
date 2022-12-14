package vestie.survey.domain.survey.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vestie.survey.domain.auth.AuthMember;
import vestie.survey.domain.survey.controller.request.SurveyRequest;
import vestie.survey.domain.survey.controller.response.ClosedSurveySimpleResponse;
import vestie.survey.domain.survey.controller.response.surveyQuery.SurveyCompleteInfoResponse;
import vestie.survey.domain.survey.controller.response.surveyQuery.SurveySimpleResponse;
import vestie.survey.domain.survey.service.SurveyQueryService;
import vestie.survey.domain.survey.controller.response.surveyResult.SurveyResultResponse;
import vestie.survey.domain.survey.service.SurveyResultService;
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
    private final SurveyResultService surveyResultService;

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

    @GetMapping("/{surveyId}/result")
    public ResponseEntity<SurveyResultResponse> getSurveyResultById(@PathVariable Long surveyId){
        SurveyResultResponse surveyResult = surveyResultService.getResultById(surveyId);
        return ResponseEntity.ok(surveyResult);
    }

    @GetMapping("/close")
    public ResponseEntity<List<ClosedSurveySimpleResponse>> getAllClosedSurveyInfo() {
        List<ClosedSurveySimpleResponse> surveyInfo = surveyResultService.getAllClosedSurveyInfo();
        return ResponseEntity.ok(surveyInfo);
    }
}
