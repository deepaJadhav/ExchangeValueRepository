package com.exchnage.service.currencyexchangedemo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


@Entity
public class Client implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty
	@Column(name="client_type")
	private String clienttype;
	
	@NotNull(message="client_number cannot be empty")
	@Column(name="client_number")
	private String clientnumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClienttype() {
		return clienttype;
	}

	public void setClienttype(String clienttype) {
		this.clienttype = clienttype;
	}

	public String getClientnumber() {
		return clientnumber;
	}

	public void setClientnumber(String clientnumber) {
		this.clientnumber = clientnumber;
	}
	
	public Client(String clienttype,String clientnumber)
	{  
		super();
		
this.clienttype=clienttype;
this.clientnumber=clientnumber;
	}
	
}
