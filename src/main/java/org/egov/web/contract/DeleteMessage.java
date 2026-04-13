package org.egov.web.contract;

import jakarta.validation.constraints.NotEmpty;

public record DeleteMessage(
	@NotEmpty String code,
	@NotEmpty String module,
	@NotEmpty String locale
) {
	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String code;
		private String module;
		private String locale;

		public Builder code(String code) {
			this.code = code;
			return this;
		}

		public Builder module(String module) {
			this.module = module;
			return this;
		}

		public Builder locale(String locale) {
			this.locale = locale;
			return this;
		}

		public DeleteMessage build() {
			return new DeleteMessage(code, module, locale);
		}
	}

	// Accessor methods for compatibility
	public String getCode() {
		return code;
	}

	public String getModule() {
		return module;
	}

	public String getLocale() {
		return locale;
	}
}
