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
        return new ErrorBean(HttpStatus.INTERNAL_SERVER_ERROR, "000",
                             this.getResourceBundle().getMessage("org.test.demo.title.internal.server.error",
                                                                 null, Locale.getDefault()),
                             e.getCause().getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorBean handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new ErrorBean(HttpStatus.BAD_REQUEST, "001",
                             this.getResourceBundle().getMessage("org.test.demo.title.parse.json",
                                                                 null, Locale.getDefault()),
                             e.getCause().getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorBean handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ErrorBean(HttpStatus.NOT_FOUND, "002",
                             this.getResourceBundle().getMessage("org.test.demo.title.resource.not.found",
                                                                 null, Locale.getDefault()),
                             e.getMessage());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorBean handleDuplicateKeyException(DuplicateKeyException e) {
        return new ErrorBean(HttpStatus.BAD_REQUEST, "003",
                             this.getResourceBundle().getMessage("org.test.demo.title.resource.duplicate.key",
                                                                 null, Locale.getDefault()),
                             e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public ErrorBean handleConstraintViolationException(ConstraintViolationException e) {
        ErrorBean errorBean = new ErrorBean(HttpStatus.NOT_ACCEPTABLE, "004",
                      this.getResourceBundle().getMessage("org.test.demo.title.resource.constraint.violation",
                                                          null, Locale.getDefault()),
                      this.getResourceBundle().getMessage("org.test.demo.detail.resource.constraint.violation",
                                                          new Object[]{e.getConstraintViolations().size()},
                                                          Locale.getDefault()));
        ValidationErrorBean validationErrorBean = new ValidationErrorBean(errorBean);
        for(ConstraintViolation<?> constraint : e.getConstraintViolations()) {
            validationErrorBean.addError(constraint.getPropertyPath().toString(),
                                         constraint.getInvalidValue(),
                                         constraint.getMessage());
        }
        return validationErrorBean;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorBean handleIllegalArgumentException(IllegalArgumentException e) {
        return new ErrorBean(HttpStatus.BAD_REQUEST, "005",
                             this.getResourceBundle().getMessage("org.test.demo.title.illegal.argument",
                                                                 null, Locale.getDefault()),
                             e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorBean handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return new ErrorBean(HttpStatus.BAD_REQUEST, "006",
                             this.getResourceBundle().getMessage("org.test.demo.title.resource.data.integrity.violation",
                                                                 null, Locale.getDefault()),
                             e.getMessage());
    }

}
