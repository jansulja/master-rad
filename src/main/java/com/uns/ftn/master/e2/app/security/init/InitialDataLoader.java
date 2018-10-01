package com.uns.ftn.master.e2.app.security.init;

import static java.util.Arrays.asList;

import java.time.LocalTime;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.uns.ftn.master.e2.app.security.authorization.component.ibac.IpBasedAccessTicket;
import com.uns.ftn.master.e2.app.security.authorization.component.rbac.RoleBasedAccessTicket;
import com.uns.ftn.master.e2.app.security.authorization.component.tbac.TimeBasedAccessTicket;
import com.uns.ftn.master.e2.app.security.dao.UserRepository;
import com.uns.ftn.master.e2.app.security.domain.AccessTicket;
import com.uns.ftn.master.e2.app.security.domain.CompoundAccessTicket;
import com.uns.ftn.master.e2.app.security.domain.User;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	
	public InitialDataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (alreadySetup)
			return;

		AccessTicket tbac1 = new TimeBasedAccessTicket(LocalTime.of(6, 15), LocalTime.of(6, 30), "/access_control_test/test6", true);
		AccessTicket tbac2 = new TimeBasedAccessTicket(LocalTime.of(8, 0), LocalTime.of(23, 0), "/access_control_test/test7", false);
		AccessTicket ibac1 = new IpBasedAccessTicket("192.168.1.78");
		AccessTicket rbac1 = new RoleBasedAccessTicket("ROLE_ADMIN");
		AccessTicket rbac2 = new RoleBasedAccessTicket("ROLE_USER");
		
		User user = new User();
		user.setId(1L);
		user.setFirstName("admin");
		user.setLastName("admin");
		user.setPassword(passwordEncoder.encode("admin"));
		user.setEmail("admin@admin.com");

		user.setCompoundAccessTickets(asList(CompoundAccessTicket.builder().accessTicket(rbac1)
				.subTicket(CompoundAccessTicket.builder().accessTicket(ibac1).build())
				.subTicket(CompoundAccessTicket.builder().accessTicket(tbac1).build())
				.subTicket(CompoundAccessTicket.builder().accessTicket(tbac2).build()).build()));

		userRepository.save(user);

		user = new User();
		user.setId(2L);
		user.setFirstName("user");
		user.setLastName("user");
		user.setPassword(passwordEncoder.encode("user"));
		user.setEmail("user@user.com");
		user.setCompoundAccessTickets(asList(CompoundAccessTicket.builder().accessTicket(rbac2).build()));

		userRepository.save(user);

		alreadySetup = true;
	}

}