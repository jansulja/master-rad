package com.uns.ftn.master.e2.app.security.domain;

import static javax.persistence.CascadeType.ALL;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CompoundAccessTicket {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(cascade=ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private AccessTicket accessTicket;
	
	@Singular
	@ManyToMany(cascade=ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<CompoundAccessTicket> subTickets;


}
