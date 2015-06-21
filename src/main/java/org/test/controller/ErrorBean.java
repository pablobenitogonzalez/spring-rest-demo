package org.test.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;
import java.util.List;
import java.util.Map;

@SuppressWarnings(ApiController.UNUSED)
public class ErrorBean {
    public Date timestamp;
    public HttpStatus status;
    public String code;
    public List<MessageBean> errors;
    public String moreInfo;

    public ErrorBean() {}

    public ErrorBean(HttpStatus status, String code, List<MessageBean> errors) {
        this.timestamp = new Date();
        this.status = status;
        this.code = code;
        this.errors = errors;
        this.moreInfo = ApiController.ERRORS_URL+"/"+code;
    }
}
