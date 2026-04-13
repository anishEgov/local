package org.egov.domain.model;

public record AuthenticatedUser(Long userId) {
	public Long getUserId() {
		return userId;
	}
}
