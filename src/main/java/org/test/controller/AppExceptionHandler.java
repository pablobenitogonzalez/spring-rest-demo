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
@SuppressWarnings( ApiController.UNUSED)
public class AppExceptionHandler extends AbstractExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorBean handleException(Exception e) {
        List<MessageBean> errors = new ArrayList<>();
        MessageBean error = new MessageBean();
        error.message = this.getResourceBundle().getMessage("org.test.demo.message.internal.server.error", null, Locale.getDefault());
        if(e.getCause()!= null) error.developerMessage = e.getCause().getMessage();
        errors.add(error);
        return new ErrorBean(HttpStatus.INTERNAL_SERVER_ERROR, "000", errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorBean handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        List<MessageBean> errors = new ArrayList<>();
        MessageBean error = new MessageBean();
        error.message = this.getResourceBundle().getMessage("org.test.demo.message.parse.json", null, Locale.getDefault());
        if(e.getCause()!= null) error.developerMessage = e.getCause().getMessage();
        errors.add(error);
        return new ErrorBean(HttpStatus.BAD_REQUEST, "001", errors);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorBean handleResourceNotFoundException(ResourceNotFoundException e) {
        List<MessageBean> errors = new ArrayList<>();
        MessageBean error = new MessageBean();
        error.message = this.getResourceBundle().getMessage("org.test.demo.message.resource.not.found", null, Locale.getDefault());
        if(e.getMessage()!= null) error.developerMessage =  e.getMessage();
        errors.add(error);
        return new ErrorBean(HttpStatus.NOT_FOUND, "002", errors);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorBean handleDuplicateKeyException(DuplicateKeyException e) {
        List<MessageBean> errors = new ArrayList<>();
        MessageBean error = new MessageBean();
        error.message = this.getResourceBundle().getMessage("org.test.demo.message.resource.duplicate.key", null, Locale.getDefault());
        if(e.getMessage()!= null) error.developerMessage = e.getMessage();
        errors.add(error);
        return new ErrorBean(HttpStatus.BAD_REQUEST, "003", errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorBean handleConstraintViolationException(ConstraintViolationException e) {
        List<MessageBean> errors = new ArrayList<>();
        for(ConstraintViolation<?> constraint : e.getConstraintViolations()) {
            MessageBean error = new MessageBean();
            error.message = this.getResourceBundle().getMessage("org.test.demo.message.resource.constraint.violation", null, Locale.getDefault());
            error.developerMessage = constraint.getPropertyPath().toString()+ " >>> " + constraint.getMessage();
            errors.add(error);
        }
        return new ErrorBean(HttpStatus.BAD_REQUEST, "004", errors);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorBean handleIllegalArgumentException(IllegalArgumentException e) {
        List<MessageBean> errors = new ArrayList<>();
        MessageBean error = new MessageBean();
        error.message = this.getResourceBundle().getMessage("org.test.demo.message.illegal.argument", null, Locale.getDefault());
        if(e.getMessage()!= null) error.developerMessage = e.getMessage();
        errors.add(error);
        return new ErrorBean(HttpStatus.BAD_REQUEST, "005", errors);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorBean handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        List<MessageBean> errors = new ArrayList<>();
        MessageBean error = new MessageBean();
        error.message = this.getResourceBundle().getMessage("org.test.demo.message.resource.data.integrity.violation", null, Locale.getDefault());
        if(e.getMessage()!= null) error.developerMessage = e.getMessage();
        errors.add(error);
        return new ErrorBean(HttpStatus.BAD_REQUEST, "006", errors);
    }

}
