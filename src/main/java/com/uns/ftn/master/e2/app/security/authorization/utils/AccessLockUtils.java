package com.uns.ftn.master.e2.app.security.authorization.utils;

import static com.uns.ftn.master.e2.app.security.authorization.component.ibac.IpBasedAccessLock.ibacBuilder;
import static com.uns.ftn.master.e2.app.security.authorization.component.ibac.IpRange.builder;
import static com.uns.ftn.master.e2.app.security.authorization.component.rbac.RoleBasedAccessLock.rbacBuilder;
import static com.uns.ftn.master.e2.app.security.authorization.component.tbac.TimeBasedAccessLock.tbacBuilder;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.substringAfter;
import static org.apache.commons.lang3.StringUtils.substringBefore;

import java.util.List;
import java.util.stream.Stream;

import com.uns.ftn.master.e2.app.security.authorization.component.ibac.IpRange;
import com.uns.ftn.master.e2.app.security.authorization.lock.AccessLock;

public class AccessLockUtils {
	
	private AccessLockUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static AccessLock rbac(String... roles) {
		
		return rbacBuilder()
				.roles(asList(roles).stream()
						.map(r -> "ROLE_" + r)
						.collect(toList()))
				.build();

	} 
	
	public static AccessLock tbac() {
		
		return tbacBuilder()
				.build();
		
	} 
	
	public static AccessLock ibac(String... ipRanges) {

		List<IpRange> ipRangesList = Stream.of(ipRanges)
				.map(r -> builder().startIpAddress(substringBefore(r, "-"))
						.endIpAddress(substringAfter(r, "-"))
						.build())
				.collect(toList());

		return ibacBuilder().ipRanges(ipRangesList)
				.build();
	}
	
}
