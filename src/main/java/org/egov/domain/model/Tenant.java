package org.egov.domain.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tenant {

	public static final String DEFAULT_TENANT = "default";
	private static final String NAMESPACE_SEPARATOR = ".";
	private String tenantId;

	public Tenant(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public List<Tenant> getTenantHierarchy() {
		var tenantHierarchy = new ArrayList<Tenant>();
		var tenantDepth = StringUtils.countMatches(tenantId, NAMESPACE_SEPARATOR);
		tenantHierarchy.add(new Tenant(tenantId));
		for (int index = tenantDepth; index >= 1; index--) {
			var tenant = tenantId.substring(0,
				StringUtils.ordinalIndexOf(tenantId, NAMESPACE_SEPARATOR, index));
			tenantHierarchy.add(new Tenant(tenant));
		}
		tenantHierarchy.add(new Tenant(DEFAULT_TENANT));
		return tenantHierarchy;
	}

	public boolean isDefaultTenant() {
		return tenantId.equals(DEFAULT_TENANT);
	}

	public boolean isMoreSpecificComparedTo(Tenant otherTenant) {
		if (tenantId.equals(otherTenant.getTenantId())) {
			return false;
		} else if (otherTenant.isDefaultTenant()) {
			return true;
		}
		return tenantId.indexOf(otherTenant.getTenantId()) == 0;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		var tenant = (Tenant) o;
		return Objects.equals(tenantId, tenant.tenantId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(tenantId);
	}

	@Override
	public String toString() {
		return "Tenant{" +
			"tenantId='" + tenantId + '\'' +
			'}';
	}
}
