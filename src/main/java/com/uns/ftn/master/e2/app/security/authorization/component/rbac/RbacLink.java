package com.uns.ftn.master.e2.app.security.authorization.component.rbac;

import static java.util.stream.Collectors.toSet;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uns.ftn.master.e2.app.security.authorization.chain.AuthorizationHandler;
import com.uns.ftn.master.e2.app.security.authorization.lock.AccessLock;
import com.uns.ftn.master.e2.app.security.domain.User;
import com.uns.ftn.master.e2.app.security.service.UserService;

@Component
public class RbacLink extends AuthorizationHandler {

	@Autowired
	private UserService userService;

	@Override
	protected Class<? extends AccessLock> getResponsibility() {
		return RoleBasedAccessLock.class;
	}

	@Override
	protected void doAuthorization(AccessLock lock) throws Exception {

		User currentUser = userService.getCurrentUser();

		Set<String> roles = userService.getAccessTicketsByType(RoleBasedAccessTicket.class, currentUser)
				.stream()
				.map(RoleBasedAccessTicket::getName)
				.collect(toSet());

		Set<String> requiredRoles = ((RoleBasedAccessLock) lock).getRoles();

		boolean anyMatch = requiredRoles.stream()
				.anyMatch(roles::contains);

		if (!anyMatch) {
			throw new Exception(String.format("Role based access failed, User roles: %s, Roles required: %s", roles,
					requiredRoles));
		}

	}

}
