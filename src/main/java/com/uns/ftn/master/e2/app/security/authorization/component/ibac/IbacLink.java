package com.uns.ftn.master.e2.app.security.authorization.component.ibac;

import static java.util.stream.Collectors.joining;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.uns.ftn.master.e2.app.security.authorization.chain.AuthorizationHandler;
import com.uns.ftn.master.e2.app.security.authorization.lock.AccessLock;
import com.uns.ftn.master.e2.app.security.domain.User;
import com.uns.ftn.master.e2.app.security.service.UserService;
import com.uns.ftn.master.e2.app.security.utils.InetAddressUtil;

@Component
public class IbacLink extends AuthorizationHandler {
	
	private UserService userService;

	public IbacLink(UserService userService) {
		this.userService = userService;
	}

	@Override
	protected Class<? extends AccessLock> getResponsibility() {
		return IpBasedAccessLock.class;
	}

	@Override
	protected void doAuthorization(AccessLock lock) throws Exception {
		
		User user = userService.getCurrentUser();
		
		List<String> userIpAddresses = userService.getAccessTicketsByType(IpBasedAccessTicket.class, user)
				.stream()
				.map(IpBasedAccessTicket::getIpAddress)
				.collect(Collectors.toList());

		Set<IpRange> ipRanges = ((IpBasedAccessLock) lock).getIpRanges();
		for (String ipAddress : userIpAddresses) {
			for (IpRange range : ipRanges ) {
				if (InetAddressUtil.isInRange(ipAddress, range.getStartIpAddress(), range.getEndIpAddress())) {
					return;
				}
			}
		}

		throw new Exception(String.format("IP base access failed, User IP adresses: %s, IP ranges allowed: %s",
				userIpAddresses.stream().collect(joining(",")),
				ipRanges.stream().map(IpRange::print).collect(joining(","))));
	}

}
