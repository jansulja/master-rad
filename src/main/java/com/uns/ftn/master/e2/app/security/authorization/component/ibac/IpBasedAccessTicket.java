package com.uns.ftn.master.e2.app.security.authorization.component.ibac;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.uns.ftn.master.e2.app.security.domain.AccessTicket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@DiscriminatorValue("IBAC")
@Entity
public class IpBasedAccessTicket extends AccessTicket {
	
    private String ipAddress;     

}