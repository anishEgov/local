package org.egov.domain.model;

import org.egov.common.contract.request.RequestInfo;

import java.util.Objects;

public class MessageRequest {

	private RequestInfo RequestInfo;

	private MessageSearchCriteria messageSearchCriteria;

	public MessageRequest() {
	}

	public MessageRequest(RequestInfo requestInfo, MessageSearchCriteria messageSearchCriteria) {
		this.RequestInfo = requestInfo;
		this.messageSearchCriteria = messageSearchCriteria;
	}

	public RequestInfo getRequestInfo() {
		return RequestInfo;
	}

	public MessageSearchCriteria getMessageSearchCriteria() {
		return messageSearchCriteria;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private RequestInfo requestInfo;
		private MessageSearchCriteria messageSearchCriteria;

		public Builder requestInfo(RequestInfo requestInfo) {
			this.requestInfo = requestInfo;
			return this;
		}

		public Builder messageSearchCriteria(MessageSearchCriteria messageSearchCriteria) {
			this.messageSearchCriteria = messageSearchCriteria;
			return this;
		}

		public MessageRequest build() {
			return new MessageRequest(requestInfo, messageSearchCriteria);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		var that = (MessageRequest) o;
		return Objects.equals(RequestInfo, that.RequestInfo) &&
			Objects.equals(messageSearchCriteria, that.messageSearchCriteria);
	}

	@Override
	public int hashCode() {
		return Objects.hash(RequestInfo, messageSearchCriteria);
	}

	@Override
	public String toString() {
		return "MessageRequest{" +
			"RequestInfo=" + RequestInfo +
			", messageSearchCriteria=" + messageSearchCriteria +
			'}';
	}
}
