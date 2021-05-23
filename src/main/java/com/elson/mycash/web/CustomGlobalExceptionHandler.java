package com.elson.mycash.web;

import com.elson.mycash.exception.UsuarioException;
import com.elson.mycash.web.dto.error.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {


        ApiError apiError= new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage("Erro de validacao");
        apiError.addValidationErrors(ex.getFieldErrors());

        return ResponseEntity
                .status(apiError.getStatus())
                .body(apiError);
        //super.handleMethodArgumentNotValid(ex, headers, status, request);
    }

    @ExceptionHandler(UsuarioException.class)
    public ResponseEntity<ApiError> handleUsuarioException(UsuarioException ex){
        ApiError apiError=new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());

        return ResponseEntity
                .status(apiError.getStatus())
                .body(apiError);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleUsuarioException(
            EntityNotFoundException ex){
        ApiError apiError=new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage("Recurso nao encontrado");

        return ResponseEntity
                .status(apiError.getStatus())
                .body(apiError);
    }

}
