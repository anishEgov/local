package org.egov.web.exception;

import org.springframework.validation.FieldError;

import java.util.List;

@SuppressWarnings("serial")
public class InvalidMessageRequest extends RuntimeException {
	private List<FieldError> errors;

	public InvalidMessageRequest(List<FieldError> errors) {
		this.errors = errors;
	}

	public List<FieldError> getErrors() {
		return errors;
	}
}
