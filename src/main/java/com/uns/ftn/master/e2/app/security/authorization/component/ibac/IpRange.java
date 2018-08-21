package com.uns.ftn.master.e2.app.security.authorization.component.ibac;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class IpRange {
	
	private String startIpAddress;
	private String endIpAddress;
	
	public String print() {
		return startIpAddress + " - " + endIpAddress;
	}
	
}