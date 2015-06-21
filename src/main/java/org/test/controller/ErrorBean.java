package org.test.controller;

import org.springframework.http.HttpStatus;

import java.util.Date;

@SuppressWarnings(ApiController.UNUSED)
public class ErrorBean {
    public Date timeStamp;
    public String moreInfo;
    public Integer statusCode;
    public HttpStatus statusDescription;
    public String errorCode;
    public String titleError;
    public String detailError;

    public ErrorBean() {}

    public ErrorBean(ErrorBean orig) {
        this.timeStamp = orig.timeStamp;
        this.moreInfo = orig.moreInfo;
        this.statusCode = orig.statusCode;
        this.statusDescription = orig.statusDescription;
        this.errorCode = orig.errorCode;
        this.titleError = orig.titleError;
        this.detailError = orig.detailError;
    }

    public ErrorBean(HttpStatus status, String errorCode, String titleError, String detailError) {
        this.timeStamp = new Date();
        this.moreInfo = ApiController.ERRORS_URL+"/"+errorCode;
        this.statusCode = status.value();
        this.statusDescription = status;
        this.errorCode = errorCode;
        this.titleError = titleError;
        this.detailError = detailError;

    }
}
