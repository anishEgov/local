package org.egov.web.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.egov.common.contract.response.ResponseInfo;

public record CacheBustResponse(
	ResponseInfo responseInfo,
	@JsonProperty("isSuccessful") boolean isSuccessful
) {
}
