package org.test.controller;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

@ControllerAdvice
@SuppressWarnings(RestPaths.UNUSED)
public class AppExceptionHandler extends AbstractExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessageBean handleException(Exception e) {
        List<Map<String, String>> errors = new ArrayList<>();
        Map<String, String> error = new LinkedHashMap<>();
        error.put("message", e.getMessage());
        errors.add(error);
        return new ErrorMessageBean("00000", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), errors);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessageBean handleResourceNotFoundException(ResourceNotFoundException e) {
        List<Map<String, String>> errors = new ArrayList<>();
        Map<String, String> error = new LinkedHashMap<>();
        error.put("message", this.getResourceBundle().getMessage("org.test.demo.message.resource.not.found", null, Locale.getDefault()));
        errors.add(error);
        return new ErrorMessageBean("00001", HttpStatus.NOT_FOUND.getReasonPhrase(), errors);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessageBean handleDuplicateKeyException(DuplicateKeyException e) {
        List<Map<String, String>> errors = new ArrayList<>();
        Map<String, String> error = new LinkedHashMap<>();
        error.put("message", this.getResourceBundle().getMessage("org.test.demo.message.resource.duplicate.key", null, Locale.getDefault()));
        error.put("property", e.getMessage());
        errors.add(error);
        return new ErrorMessageBean("00002", HttpStatus.BAD_REQUEST.getReasonPhrase(), errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessageBean handleConstraintViolationException(ConstraintViolationException e) {
        List<Map<String, String>> errors = new ArrayList<>();
        for(ConstraintViolation<?> constraint : e.getConstraintViolations()) {
            Map<String, String> error = new LinkedHashMap<>();
            error.put("message", this.getResourceBundle().getMessage("org.test.demo.message.resource.constraint.violation", null, Locale.getDefault()));
            error.put("property", constraint.getPropertyPath().toString());
            error.put("violation", constraint.getMessage());
            errors.add(error);
        }
        return new ErrorMessageBean("00003", HttpStatus.BAD_REQUEST.getReasonPhrase(), errors);
    }

}
