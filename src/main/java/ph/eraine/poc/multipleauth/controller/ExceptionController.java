package ph.eraine.poc.multipleauth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ph.eraine.poc.multipleauth.dto.ApiErrorDetail;
import ph.eraine.poc.multipleauth.dto.ApiErrorResponse;
import ph.eraine.poc.multipleauth.exception.ValidationException;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> exception(Exception ex) {
        ApiErrorResponse response = new ApiErrorResponse();
        response.add(new ApiErrorDetail("ERR01", ex.getMessage()));
        log.info("Error: ", ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiErrorResponse> validationException(ValidationException ex) {
        ApiErrorResponse response = new ApiErrorResponse();
        ex.getErrors().forEach((code, message) -> response.add(new ApiErrorDetail(code, message)));
        log.info("Error messages: {}", ex.getErrors());
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(response);
    }

}