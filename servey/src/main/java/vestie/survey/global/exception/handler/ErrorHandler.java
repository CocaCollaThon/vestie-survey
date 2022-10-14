package vestie.survey.global.exception.handler;

import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import vestie.survey.global.exception.FailedAuthenticationException;

@RestControllerAdvice
public class ErrorHandler{

    @ExceptionHandler(FailedAuthenticationException.class)
    public ResponseEntity<ErrorMessage> failedAuthenticationException(FailedAuthenticationException e){
        return ResponseEntity.status(e.getStatus())
                .body(ErrorMessageFactory.from(e.getStatus(), e.getErrorCode(),e.getErrorMessage()));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorMessage> handleBindingException(ValidationException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorMessageFactory.from(HttpStatus.BAD_REQUEST, 9999, e.getMessage()));
    }
}
