package org.test.controller;

import java.util.List;
import java.util.Map;

@SuppressWarnings(RestPaths.UNUSED)
public class ErrorMessageBean {
    public String code;
    public String status;
    public List<Map<String, String>> errors;

    public ErrorMessageBean() {}

    public ErrorMessageBean(String code, String status, List<Map<String, String>> errors) {
        this.code = code;
        this.status = status;
        this.errors = errors;
    }
}
