package com.elson.mycash.web.dto.error;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ApiError {

    private HttpStatus status;
    private LocalDateTime dataHora;
    private String message;
    private List<String> subErros;

    public List<String> getSubErros() {
        return subErros;
    }

    public void setSubErros(List<String> subErros) {
        this.subErros = subErros;
    }

    public ApiError(HttpStatus status) {
        this.status=status;
        this.dataHora = LocalDateTime.now();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void addValidationErrors(List<FieldError> fieldErrors) {
        this.subErros=fieldErrors
                .stream()
                .map(fe->fe.getDefaultMessage())
                .collect(Collectors.toList());

    }
}
