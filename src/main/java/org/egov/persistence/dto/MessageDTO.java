package org.egov.persistence.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.egov.domain.model.Message;
import org.egov.domain.model.MessageIdentity;
import org.egov.domain.model.Tenant;

import java.io.Serializable;

public record MessageDTO(
	String code,
	String locale,
	String module,
	String tenant,
	String message
) implements Serializable {

	public MessageDTO(Message domainMessage) {
		this(
			domainMessage.getCode(),
			domainMessage.getLocale(),
			domainMessage.getModule(),
			domainMessage.getTenant(),
			domainMessage.getMessage()
		);
	}

	@JsonIgnore
	public Message toDomainMessage() {
		var tenantObj = new Tenant(tenant);
		var messageIdentity = MessageIdentity.builder()
			.code(code)
			.module(module)
			.locale(locale)
			.tenant(tenantObj)
			.build();
		return Message.builder()
			.messageIdentity(messageIdentity)
			.message(message)
			.build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String code;
		private String locale;
		private String module;
		private String tenant;
		private String message;

		public Builder code(String code) {
			this.code = code;
			return this;
		}

		public Builder locale(String locale) {
			this.locale = locale;
			return this;
		}

		public Builder module(String module) {
			this.module = module;
			return this;
		}

		public Builder tenant(String tenant) {
			this.tenant = tenant;
			return this;
		}

		public Builder message(String message) {
			this.message = message;
			return this;
		}

		public MessageDTO build() {
			return new MessageDTO(code, locale, module, tenant, message);
		}
	}
}
