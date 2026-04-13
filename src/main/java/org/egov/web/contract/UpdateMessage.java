package org.egov.web.contract;

import jakarta.validation.constraints.NotEmpty;

public record UpdateMessage(
	@NotEmpty String code,
	@NotEmpty String message
) {
	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String code;
		private String message;

		public Builder code(String code) {
			this.code = code;
			return this;
		}

		public Builder message(String message) {
			this.message = message;
			return this;
		}

		public UpdateMessage build() {
			return new UpdateMessage(code, message);
		}
	}

	// Accessor methods for compatibility
	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
