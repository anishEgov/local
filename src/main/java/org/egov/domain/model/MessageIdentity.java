package org.egov.domain.model;

public record MessageIdentity(String code, Tenant tenant, String locale, String module) {
	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String code;
		private Tenant tenant;
		private String locale;
		private String module;

		public Builder code(String code) {
			this.code = code;
			return this;
		}

		public Builder tenant(Tenant tenant) {
			this.tenant = tenant;
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

		public MessageIdentity build() {
			return new MessageIdentity(code, tenant, locale, module);
		}
	}

	// Accessor methods for compatibility
	public String getCode() {
		return code;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public String getLocale() {
		return locale;
	}

	public String getModule() {
		return module;
	}
}
