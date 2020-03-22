package eu.kaszkowiak.demostore.common;

import eu.kaszkowiak.demostore.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerErrorAdvice {

    @ExceptionHandler(value = IllegalArgumentException.class)
    protected ResponseEntity<ErrorDto> handleBadRequest(IllegalArgumentException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorDto(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotFoundException.class)
    protected ResponseEntity<ErrorDto> handleBadRequest(NotFoundException ex, WebRequest request) {
        var errorMessage = ex.getEntity() != null
                ? ex.getEntity().getClass().getSimpleName() + " could not be found"
                : null;
        return new ResponseEntity<>(new ErrorDto(errorMessage), HttpStatus.NOT_FOUND);
    }
}
