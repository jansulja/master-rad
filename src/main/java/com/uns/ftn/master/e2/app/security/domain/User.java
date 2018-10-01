package com.uns.ftn.master.e2.app.security.domain;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class User {
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
 
    @JsonIgnore
    private String firstName;
    @JsonIgnore
    private String lastName;
    private String email;
    private String password;
    
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade=CascadeType.ALL)
    private Collection<CompoundAccessTicket> compoundAccessTickets;
    
}