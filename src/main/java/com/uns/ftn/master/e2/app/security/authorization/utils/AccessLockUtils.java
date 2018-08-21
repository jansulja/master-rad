package com.uns.ftn.master.e2.app.security.authorization.utils;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.substringAfter;
import static org.apache.commons.lang3.StringUtils.substringBefore;

import java.util.List;
import java.util.stream.Stream;

import com.uns.ftn.master.e2.app.security.authorization.component.ibac.IpBasedAccessLock;
import com.uns.ftn.master.e2.app.security.authorization.component.ibac.IpRange;
import com.uns.ftn.master.e2.app.security.authorization.component.rbac.RoleBasedAccessLock;
import com.uns.ftn.master.e2.app.security.authorization.component.tbac.TimeBasedAccessLock;
import com.uns.ftn.master.e2.app.security.authorization.lock.AccessLock;

public class AccessLockUtils {
	
	private AccessLockUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static AccessLock rbac(String... roles) {
		
		return RoleBasedAccessLock.rbacBuilder()
				.roles(asList(roles).stream()
						.map(r -> "ROLE_" + r)
						.collect(toList()))
				.build();

	} 
	
	public static AccessLock tbac() {
		
		return TimeBasedAccessLock.tbacBuilder()
				.build();
		
	} 
	
	public static AccessLock ibac(String... ipRanges) {

		List<IpRange> ipRangesList = Stream.of(ipRanges)
				.map(r -> IpRange.builder().startIpAddress(substringBefore(r, "-"))
						.endIpAddress(substringAfter(r, "-"))
						.build())
				.collect(toList());

		return IpBasedAccessLock.ibacBuilder().ipRanges(ipRangesList)
				.build();
	}
	
}
