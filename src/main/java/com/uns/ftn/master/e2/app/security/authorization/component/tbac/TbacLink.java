package com.uns.ftn.master.e2.app.security.authorization.component.tbac;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.uns.ftn.master.e2.app.security.authorization.chain.AuthorizationHandler;
import com.uns.ftn.master.e2.app.security.authorization.lock.AccessLock;
import com.uns.ftn.master.e2.app.security.domain.User;
import com.uns.ftn.master.e2.app.security.service.UserService;

@Component
public class TbacLink extends AuthorizationHandler {

	@Autowired
	private UserService userService;

	@Override
	protected Class<? extends AccessLock> getResponsibility() {
		return TimeBasedAccessLock.class;
	}

	@Override
	protected void doAuthorization(AccessLock lock) throws Exception {
		
		User user = userService.getCurrentUser();
		String path = ServletUriComponentsBuilder.fromCurrentRequest().build().getPath();
		
		List<TimeBasedAccessTicket> collect = userService.getAccessTicketsByType(TimeBasedAccessTicket.class, user)
				.stream()
				.filter(p -> p.getResourceURI().equals(path))
				.collect(Collectors.toList());
		
		for (TimeBasedAccessTicket ticket : collect) {
			
			LocalTime startTime = ticket.getStartTime();
			LocalTime endTime = ticket.getEndTime();
			LocalTime now = LocalTime.now();

			if (ticket.getAllowed()) {

				if (now.isBefore(startTime) || now.isAfter(endTime)) {
					throw new Exception(String.format("Time based access failed, ticket: %s, current time: %s", ticket, now));
				}

			} else {

				if (now.isAfter(startTime) && now.isBefore(endTime)) {
					throw new Exception(String.format("Time based access failed, ticket: %s, current time: %s", ticket, now));
				}

			}

		}

	}

}
