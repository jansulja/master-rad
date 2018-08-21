package com.uns.ftn.master.e2.app.security.authorization.component.rbac;

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
@DiscriminatorValue("RBAC")
@Entity
public class RoleBasedAccessTicket extends AccessTicket {
	
    private String name;     

}