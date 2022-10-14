package vestie.survey.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public class FailedAuthenticationException extends RuntimeException{
    private final HttpStatus status = HttpStatus.BAD_REQUEST;
    private final int errorCode = 4;
    private final String errorMessage;

}
