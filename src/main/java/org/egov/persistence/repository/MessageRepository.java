package org.egov.persistence.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.egov.domain.model.AuthenticatedUser;
import org.egov.domain.model.Message;
import org.egov.domain.model.Tenant;
import org.egov.tracer.model.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class MessageRepository {

	private static final Logger log = LoggerFactory.getLogger(MessageRepository.class);
	private MessageJpaRepository messageJpaRepository;

	public MessageRepository(MessageJpaRepository messageJpaRepository) {
		this.messageJpaRepository = messageJpaRepository;
	}

	public List<Message> findByTenantIdAndLocale(Tenant tenant, String locale) {
		return messageJpaRepository.find(tenant.getTenantId(), locale).stream()
				.map(org.egov.persistence.entity.Message::toDomain).collect(Collectors.toList());
	}

	public List<Message> findAllMessage(Tenant tenant, String locale, String module, String code) {
		return messageJpaRepository.find(tenant.getTenantId(), locale, module, code).stream()
				.map(org.egov.persistence.entity.Message::toDomain).collect(Collectors.toList());
	}

	
	public void setUUID(List<org.egov.persistence.entity.Message> entityMessages){
		for(var message : entityMessages){
			message.setId(UUID.randomUUID().toString());
		}
	}
	
	public void save(List<Message> messages, AuthenticatedUser authenticatedUser) {
		var entityMessages = messages.stream()
				.map(org.egov.persistence.entity.Message::new).collect(Collectors.toList());
		setAuditFieldsForCreate(authenticatedUser, entityMessages);
		//Setting ID in UUID
		setUUID(entityMessages);
		log.info("entityMessages: "+entityMessages);
		try {
			messageJpaRepository.saveAll(entityMessages);
		} catch (DataIntegrityViolationException ex) {
			new DataIntegrityViolationExceptionTransformer(ex).transform();
		}
	}

	public void delete(String tenant, String locale, String module, List<String> codes) {
		var messages = messageJpaRepository.find(tenant, locale, module, codes);
		if (CollectionUtils.isEmpty(messages)) {
			return;
		}
		messageJpaRepository.deleteAll(messages);
	}

	public void update(String tenant, String locale, String module, List<Message> domainMessages,
			AuthenticatedUser authenticatedUser) {
		var codes = getCodes(domainMessages);
		var entityMessages = fetchMatchEntityMessages(tenant, locale, module, codes);
		updateMessages(domainMessages, entityMessages, authenticatedUser);
	}

	public void upsert(String tenant, String locale, String module, List<Message> domainMessages,
			AuthenticatedUser authenticatedUser) {
		var codes = getCodes(domainMessages);
		var entityMessages = fetchMatchEntityMessages(tenant, locale, module, codes);
		var newCodes = getNewCodes(entityMessages);
		var newMsgList = domainMessages.stream().filter(msg -> !newCodes.contains(msg.getCode()))
				.collect(Collectors.toList());
		save(newMsgList, authenticatedUser);

		updateMessages(domainMessages, entityMessages, authenticatedUser);

	}


	private void setAuditFieldsForCreate(AuthenticatedUser authenticatedUser,
			List<org.egov.persistence.entity.Message> entityMessages) {
		entityMessages.forEach(message -> {
			message.setCreatedDate(new Date());
			message.setCreatedBy(authenticatedUser.getUserId());
		});
	}

	private List<org.egov.persistence.entity.Message> fetchMatchEntityMessages(String tenant, String locale,
			String module, List<String> codes) {
		return messageJpaRepository.find(tenant, locale, module, codes);
	}

	private void updateMessages(List<Message> domainMessages, List<org.egov.persistence.entity.Message> entityMessages,
			AuthenticatedUser authenticatedUser) {
		var codeToMessageMap = getCodeToMessageMap(domainMessages);
		entityMessages.stream().forEach(entityMessage -> {
			var matchingMessage = codeToMessageMap.get(entityMessage.getCode());
			entityMessage.update(matchingMessage);
			setAuditFieldsForUpdate(authenticatedUser, entityMessage);

		});
		messageJpaRepository.saveAll(entityMessages);
	}

	private void setAuditFieldsForUpdate(AuthenticatedUser authenticatedUser,
			org.egov.persistence.entity.Message entityMessage) {
		entityMessage.setLastModifiedBy(authenticatedUser.getUserId());
		entityMessage.setLastModifiedDate(new Date());
	}

	private Map<String, Message> getCodeToMessageMap(List<Message> messages) {
		try{
			return messages.stream().collect(Collectors.toMap(Message::getCode, message -> message));
		}catch (Exception e){
			throw new CustomException("DUPLICATE_RECORDS",e.getMessage());
		}
	}

	private List<String> getCodes(List<Message> messages) {
		return messages.stream().map(Message::getCode).toList();
	}

	private List<String> getNewCodes(List<org.egov.persistence.entity.Message> messages) {
		return messages.stream().map(org.egov.persistence.entity.Message::getCode).toList();
	}
}
