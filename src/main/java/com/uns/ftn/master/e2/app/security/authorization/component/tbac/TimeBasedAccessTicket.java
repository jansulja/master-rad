package com.uns.ftn.master.e2.app.security.authorization.component.tbac;

import java.time.LocalTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.uns.ftn.master.e2.app.security.domain.AccessTicket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@DiscriminatorValue("TBAC")
@Entity
@ToString
public class TimeBasedAccessTicket extends AccessTicket{

	private LocalTime startTime;
	private LocalTime endTime;
	private String resourceURI;
	private Boolean allowed;
	
	
}
