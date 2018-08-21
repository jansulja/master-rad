package com.uns.ftn.master.e2.app.security.authorization.chain;

import static java.util.Objects.nonNull;

import com.uns.ftn.master.e2.app.security.authorization.lock.AccessLock;

public abstract class AuthorizationHandler {

	protected AuthorizationHandler successor;

	protected abstract Class<? extends AccessLock> getResponsibility();
	protected abstract void doAuthorization(AccessLock lock) throws Exception;
	
	public void setSuccessor(AuthorizationHandler successor) {
		this.successor = successor;
	}
	
	public void handleRequest(AccessLock lock) throws Exception {
				
		if(lock.getClass().equals(this.getResponsibility())) {
			this.doAuthorization(lock);
		}
		
		if(nonNull(successor)) {
			successor.handleRequest(lock);
		}
		
	}
	
	
	
	
}
