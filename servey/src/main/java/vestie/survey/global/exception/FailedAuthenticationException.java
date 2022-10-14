package vestie.survey.global.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class FailedAuthenticationException extends RuntimeException{
    private final HttpStatus status = HttpStatus.UNAUTHORIZED;
    private final int errorCode = 4;
    private final String errorMessage;

}
