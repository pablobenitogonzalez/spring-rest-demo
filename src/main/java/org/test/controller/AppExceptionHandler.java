package org.test.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
        error.put("message", this.getResourceBundle().getMessage("org.test.demo.message.internal.server.error", null, Locale.getDefault()));
        if(e.getCause()!= null) error.put("info", e.getCause().getMessage());
        errors.add(error);
        return new ErrorMessageBean("000", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase().toLowerCase(), errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessageBean handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        List<Map<String, String>> errors = new ArrayList<>();
        Map<String, String> error = new LinkedHashMap<>();
        error.put("message", this.getResourceBundle().getMessage("org.test.demo.message.parse.json", null, Locale.getDefault()));
        if(e.getCause()!= null) error.put("info", e.getCause().getMessage());
        errors.add(error);
        return new ErrorMessageBean("001", HttpStatus.BAD_REQUEST.getReasonPhrase().toLowerCase(), errors);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessageBean handleResourceNotFoundException(ResourceNotFoundException e) {
        List<Map<String, String>> errors = new ArrayList<>();
        Map<String, String> error = new LinkedHashMap<>();
        error.put("message", this.getResourceBundle().getMessage("org.test.demo.message.resource.not.found", null, Locale.getDefault()));
        if(e.getMessage()!= null) error.put("info", e.getMessage());
        errors.add(error);
        return new ErrorMessageBean("002", HttpStatus.NOT_FOUND.getReasonPhrase().toLowerCase(), errors);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessageBean handleDuplicateKeyException(DuplicateKeyException e) {
        List<Map<String, String>> errors = new ArrayList<>();
        Map<String, String> error = new LinkedHashMap<>();
        error.put("message", this.getResourceBundle().getMessage("org.test.demo.message.resource.duplicate.key", null, Locale.getDefault()));
        if(e.getMessage()!= null) error.put("info", e.getMessage());
        errors.add(error);
        return new ErrorMessageBean("003", HttpStatus.BAD_REQUEST.getReasonPhrase().toLowerCase(), errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessageBean handleConstraintViolationException(ConstraintViolationException e) {
        List<Map<String, String>> errors = new ArrayList<>();
        for(ConstraintViolation<?> constraint : e.getConstraintViolations()) {
            Map<String, String> error = new LinkedHashMap<>();
            error.put("message", this.getResourceBundle().getMessage("org.test.demo.message.resource.constraint.violation", null, Locale.getDefault()));
            error.put("info", constraint.getPropertyPath().toString()+ " >>> " + constraint.getMessage());
            errors.add(error);
        }
        return new ErrorMessageBean("004", HttpStatus.BAD_REQUEST.getReasonPhrase().toLowerCase(), errors);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessageBean handleIllegalArgumentException(IllegalArgumentException e) {
        List<Map<String, String>> errors = new ArrayList<>();
        Map<String, String> error = new LinkedHashMap<>();
        error.put("message", this.getResourceBundle().getMessage("org.test.demo.message.illegal.argument", null, Locale.getDefault()));
        if(e.getMessage()!= null) error.put("info", e.getMessage());
        errors.add(error);
        return new ErrorMessageBean("005", HttpStatus.BAD_REQUEST.getReasonPhrase().toLowerCase(), errors);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessageBean handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        List<Map<String, String>> errors = new ArrayList<>();
        Map<String, String> error = new LinkedHashMap<>();
        error.put("message", this.getResourceBundle().getMessage("org.test.demo.message.resource.data.integrity.violation", null, Locale.getDefault()));
        if(e.getMessage()!= null) error.put("info", e.getMessage());
        errors.add(error);
        return new ErrorMessageBean("006", HttpStatus.BAD_REQUEST.getReasonPhrase().toLowerCase(), errors);
    }

}
