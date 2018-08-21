package com.uns.ftn.master.e2.app.security.authorization.component.ibac;

import java.util.Set;

import com.uns.ftn.master.e2.app.security.authorization.lock.AccessLock;

import lombok.Builder;
import lombok.Singular;

@Builder(builderMethodName="ibacBuilder")
public class IpBasedAccessLock implements AccessLock {

	@Singular
	private Set<IpRange> ipRanges;

	public Set<IpRange> getIpRanges() {
		return ipRanges;
	}
	
}
