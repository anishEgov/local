package org.egov.domain.model;

import jakarta.validation.constraints.Null;

public class Message {
	private String message;

	@Null
	private MessageIdentity messageIdentity;

	public Message(String message, MessageIdentity messageIdentity) {
		this.message = message;
		this.messageIdentity = messageIdentity;
	}

	public boolean isMoreSpecificComparedTo(Message otherMessage) {
		var otherTenant = otherMessage.getMessageIdentity().getTenant();
		return messageIdentity.getTenant().isMoreSpecificComparedTo(otherTenant);
	}

	public String getCode() {
		return messageIdentity.getCode();
	}

	public String getModule() {
		return messageIdentity.getModule();
	}

	public String getLocale() {
		return messageIdentity.getLocale();
	}

	public String getTenant() {
		return messageIdentity.getTenant().getTenantId();
	}

	public String getMessage() {
		return message;
	}

	public MessageIdentity getMessageIdentity() {
		return messageIdentity;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String message;
		private MessageIdentity messageIdentity;

		public Builder message(String message) {
			this.message = message;
			return this;
		}

		public Builder messageIdentity(MessageIdentity messageIdentity) {
			this.messageIdentity = messageIdentity;
			return this;
		}

		public Message build() {
			return new Message(message, messageIdentity);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		var message1 = (Message) o;
		return java.util.Objects.equals(message, message1.message) &&
			java.util.Objects.equals(messageIdentity, message1.messageIdentity);
	}

	@Override
	public int hashCode() {
		return java.util.Objects.hash(message, messageIdentity);
	}

	@Override
	public String toString() {
		return "Message{" +
			"message='" + message + '\'' +
			", messageIdentity=" + messageIdentity +
			'}';
	}
}
