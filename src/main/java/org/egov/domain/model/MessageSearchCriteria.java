package org.egov.domain.model;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.util.Objects;
import java.util.Set;

import jakarta.validation.constraints.Size;

public class MessageSearchCriteria {
	
	private Tenant tenantId;

	@Size(max = 255)
	private String locale;

	@Size(max = 255)
	private String module;
	
	private Set<String> codes;

	public MessageSearchCriteria() {
	}

	public MessageSearchCriteria(Tenant tenantId, String locale, String module, Set<String> codes) {
		this.tenantId = tenantId;
		this.locale = locale;
		this.module = module;
		this.codes = codes;
	}

	public Tenant getTenantId() {
		return tenantId;
	}

	public String getLocale() {
		return locale;
	}

	public String getModule() {
		return module;
	}

	public Set<String> getCodes() {
		return codes;
	}

	public boolean isModuleAbsent() {
		return isEmpty(module);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private Tenant tenantId;
		private String locale;
		private String module;
		private Set<String> codes;

		public Builder tenantId(Tenant tenantId) {
			this.tenantId = tenantId;
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

		public Builder codes(Set<String> codes) {
			this.codes = codes;
			return this;
		}

		public MessageSearchCriteria build() {
			return new MessageSearchCriteria(tenantId, locale, module, codes);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		var that = (MessageSearchCriteria) o;
		return Objects.equals(tenantId, that.tenantId) &&
			Objects.equals(locale, that.locale) &&
			Objects.equals(module, that.module) &&
			Objects.equals(codes, that.codes);
	}

	@Override
	public int hashCode() {
		return Objects.hash(tenantId, locale, module, codes);
	}

	@Override
	public String toString() {
		return "MessageSearchCriteria{" +
			"tenantId=" + tenantId +
			", locale='" + locale + '\'' +
			", module='" + module + '\'' +
			", codes=" + codes +
			'}';
	}
}
