package org.test.controller;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@SuppressWarnings(ApiController.UNUSED)
public class ValidationErrorBean extends ErrorBean {

	@JsonInclude(NON_EMPTY)
	public List<Error> errors = new ArrayList<>();

	public ValidationErrorBean(ErrorBean orig) {
		super(orig);
	}

	public void addError(String field, Object invalidValue, String message) {
		Error error = new Error();
		error.field = field;
		error.invalidValue = invalidValue;
		error.message = message;
		errors.add(error);
	}

	public void addError(String message) {
		Error error = new Error();
		error.message = message;
		errors.add(error);
	}

	@JsonInclude(NON_EMPTY)
	static class Error {
		public String field;
		public Object invalidValue;
		public String message;
	}
}