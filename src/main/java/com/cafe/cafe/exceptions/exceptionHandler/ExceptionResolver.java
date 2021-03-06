package com.cafe.cafe.exceptions.exceptionHandler;


import com.cafe.cafe.exceptions.dto.ErrorDetails;
import com.cafe.cafe.exceptions.simpleException.BadRequestException;
import com.cafe.cafe.exceptions.simpleException.NotAllowedException;
import com.cafe.cafe.exceptions.simpleException.NotFoundException;
import com.cafe.cafe.exceptions.simpleException.TeapotException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@ControllerAdvice
@RestController
public class ExceptionResolver extends ResponseEntityExceptionHandler {


    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ErrorDetails> handleBadRequest(Exception ex, WebRequest request) {
        ErrorDetails exceptionResponse = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleUnauthorized(Exception ex, WebRequest request) {
        ErrorDetails exceptionResponse = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotAllowedException.class)
    public final ResponseEntity<ErrorDetails> handleNotAllowed(Exception ex, WebRequest request) {
        ErrorDetails exceptionResponse = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(TeapotException.class)
    public final ResponseEntity<ErrorDetails> handleTeapot(Exception ex, WebRequest request) {
        ErrorDetails exceptionResponse = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.I_AM_A_TEAPOT);
    }


}
