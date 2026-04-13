package org.egov.web.contract;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.egov.domain.model.MessageIdentity;
import org.egov.domain.model.Tenant;
import jakarta.validation.constraints.NotEmpty;

public class Message {
	@NotEmpty
	private String code;
	@NotEmpty
	private String message;
	@NotEmpty
	private String module;
	@NotEmpty
	private String locale;

	public Message() {
	}

	public Message(String code, String message, String module, String locale) {
		this.code = code;
		this.message = message;
		this.module = module;
		this.locale = locale;
	}

	public Message(org.egov.domain.model.Message domainMessage) {
		this.code = domainMessage.getCode();
		this.message = domainMessage.getMessage();
		this.module = domainMessage.getModule();
		this.locale = domainMessage.getLocale();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	@JsonIgnore
	public MessageIdentity getMessageIdentity(Tenant tenant) {
		return MessageIdentity.builder()
			.code(code)
			.module(module)
			.locale(locale)
			.tenant(tenant)
			.build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String code;
		private String message;
		private String module;
		private String locale;

		public Builder code(String code) {
			this.code = code;
			return this;
		}

		public Builder message(String message) {
			this.message = message;
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

		public Message build() {
			return new Message(code, message, module, locale);
		}
	}
}
