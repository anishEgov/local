package org.egov.persistence.entity;

import jakarta.persistence.*;
import org.egov.domain.model.MessageIdentity;
import org.egov.domain.model.Tenant;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "message")
@SequenceGenerator(name = Message.SEQ_MESSAGE, sequenceName = Message.SEQ_MESSAGE, allocationSize = 1)
public class Message {

	static final String SEQ_MESSAGE = "SEQ_MESSAGE";

	@Id
	private String id;

	@Column(name = "locale")
	private String locale;

	@Column(name = "code")
	private String code;

	@Column(name = "module")
	private String module;

	@Column(name = "message")
	private String message;

	@Column(name = "tenantid")
	private String tenantId;

	@Column(name = "createdby")
	private Long createdBy;

	@Column(name = "createddate")
	private Date createdDate;

	@Column(name = "lastmodifiedby")
	private Long lastModifiedBy;

	@Column(name = "lastmodifieddate")
	private Date lastModifiedDate;

	public Message() {
	}

	public Message(String id, String locale, String code, String module, String message, String tenantId,
				   Long createdBy, Date createdDate, Long lastModifiedBy, Date lastModifiedDate) {
		this.id = id;
		this.locale = locale;
		this.code = code;
		this.module = module;
		this.message = message;
		this.tenantId = tenantId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
	}

	public Message(org.egov.domain.model.Message domainMessage) {
		this.tenantId = domainMessage.getTenant();
		this.locale = domainMessage.getLocale();
		this.module = domainMessage.getModule();
		this.code = domainMessage.getCode();
		this.message = domainMessage.getMessage();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(Long lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public org.egov.domain.model.Message toDomain() {
		var tenant = new Tenant(tenantId);
		var messageIdentity = MessageIdentity.builder().tenant(tenant).module(module).locale(locale)
			.code(code).build();
		return org.egov.domain.model.Message.builder().message(message).messageIdentity(messageIdentity).build();
	}

	public void update(org.egov.domain.model.Message updatedMessage) {
		message = updatedMessage.getMessage();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String id;
		private String locale;
		private String code;
		private String module;
		private String message;
		private String tenantId;
		private Long createdBy;
		private Date createdDate;
		private Long lastModifiedBy;
		private Date lastModifiedDate;

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder locale(String locale) {
			this.locale = locale;
			return this;
		}

		public Builder code(String code) {
			this.code = code;
			return this;
		}

		public Builder module(String module) {
			this.module = module;
			return this;
		}

		public Builder message(String message) {
			this.message = message;
			return this;
		}

		public Builder tenantId(String tenantId) {
			this.tenantId = tenantId;
			return this;
		}

		public Builder createdBy(Long createdBy) {
			this.createdBy = createdBy;
			return this;
		}

		public Builder createdDate(Date createdDate) {
			this.createdDate = createdDate;
			return this;
		}

		public Builder lastModifiedBy(Long lastModifiedBy) {
			this.lastModifiedBy = lastModifiedBy;
			return this;
		}

		public Builder lastModifiedDate(Date lastModifiedDate) {
			this.lastModifiedDate = lastModifiedDate;
			return this;
		}

		public Message build() {
			return new Message(id, locale, code, module, message, tenantId, createdBy, createdDate, lastModifiedBy, lastModifiedDate);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		var message1 = (Message) o;
		return Objects.equals(id, message1.id) &&
			Objects.equals(locale, message1.locale) &&
			Objects.equals(code, message1.code) &&
			Objects.equals(module, message1.module) &&
			Objects.equals(message, message1.message) &&
			Objects.equals(tenantId, message1.tenantId) &&
			Objects.equals(createdBy, message1.createdBy) &&
			Objects.equals(createdDate, message1.createdDate) &&
			Objects.equals(lastModifiedBy, message1.lastModifiedBy) &&
			Objects.equals(lastModifiedDate, message1.lastModifiedDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, locale, code, module, message, tenantId, createdBy, createdDate, lastModifiedBy, lastModifiedDate);
	}

	@Override
	public String toString() {
		return "Message{" +
			"id='" + id + '\'' +
			", locale='" + locale + '\'' +
			", code='" + code + '\'' +
			", module='" + module + '\'' +
			", message='" + message + '\'' +
			", tenantId='" + tenantId + '\'' +
			", createdBy=" + createdBy +
			", createdDate=" + createdDate +
			", lastModifiedBy=" + lastModifiedBy +
			", lastModifiedDate=" + lastModifiedDate +
			'}';
	}
}
