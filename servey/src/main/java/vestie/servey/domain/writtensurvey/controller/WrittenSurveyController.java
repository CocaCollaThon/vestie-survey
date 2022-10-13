package vestie.servey.domain.writtensurvey.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vestie.servey.domain.AuthMember;
import vestie.servey.global.web.argumentresolver.auth.Auth;

@RestController
@RequestMapping("/api")
public class WrittenSurveyController {

    @GetMapping("/v1/written")
    public ResponseEntity<AuthMember> getInfo(@Auth AuthMember authMember){
        return ResponseEntity.ok(authMember);
    }
}
