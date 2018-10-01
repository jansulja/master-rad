package com.uns.ftn.master.e2.app.security.utils;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.uns.ftn.master.e2.app.security.domain.AccessTicket;
import com.uns.ftn.master.e2.app.security.domain.CompoundAccessTicket;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CompoundAccessTicketUtils {
		
	public static List<AccessTicket> extractAccessTickets(Collection<CompoundAccessTicket> compounAccessTickets) {
		
		List<AccessTicket> extractedAccessTickets = new ArrayList<>();
		compounAccessTickets.stream().forEach(cat -> {
			extractedAccessTickets.add(cat.getAccessTicket());
			if(isNotEmpty(cat.getSubTickets())) {
				extractedAccessTickets.addAll(extractAccessTickets(cat.getSubTickets()));
			}
		});
		
		return extractedAccessTickets;		
    }
	
	
}
