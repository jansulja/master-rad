package com.uns.ftn.master.e2.app.security.authorization.component.rbac;

import java.util.Set;

import com.uns.ftn.master.e2.app.security.authorization.lock.AccessLock;

import lombok.Builder;
import lombok.Singular;

@Builder(builderMethodName="rbacBuilder")
public class RoleBasedAccessLock implements AccessLock {

	
	
	@Singular
	private Set<String> roles;

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}



	


	

	
	


	
}