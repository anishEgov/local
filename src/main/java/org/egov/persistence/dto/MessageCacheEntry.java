package org.egov.persistence.dto;

import java.io.Serializable;
import java.util.List;

import org.egov.domain.model.Message;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MessageCacheEntry implements Serializable {
	private List<MessageDTO> messages;

	public MessageCacheEntry() {
	}

	public MessageCacheEntry(List<Message> domainMessages) {
		this.messages = domainMessages.stream()
			.map(MessageDTO::new)
			.toList();
	}

	@JsonIgnore
	public List<Message> getDomainMessages() {
		return messages.stream()
			.map(MessageDTO::toDomainMessage)
			.toList();
	}

	public List<MessageDTO> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageDTO> messages) {
		this.messages = messages;
	}
}
