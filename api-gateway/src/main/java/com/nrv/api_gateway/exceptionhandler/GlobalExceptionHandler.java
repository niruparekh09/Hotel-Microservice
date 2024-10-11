package com.nrv.api_gateway.exceptionhandler;

import com.nrv.api_gateway.exception.InvalidRequestException;
import com.nrv.api_gateway.exception.NotAuthorizedException;
import com.nrv.api_gateway.response.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to handle any exception thrown in our server. This is a global level so it will
 * handle all exceptions thrown at any level. It can also handle custom exceptions.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
@Component
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<APIResponse> handleTypeMismatch(TypeMismatchException ex) {
        log.error("Invalid UUID format");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse("Invalid UUID format"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse(errors.toString()));
    }

    /*Custom Exceptions*/
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<?> handleInvalidRequest(InvalidRequestException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse(ex.getMessage()));
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<?> handleNotAuthorized(NotAuthorizedException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new APIResponse(ex.getMessage()));
    }

    /*All other exceptions*/
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse(e.getMessage()));
    }
}
