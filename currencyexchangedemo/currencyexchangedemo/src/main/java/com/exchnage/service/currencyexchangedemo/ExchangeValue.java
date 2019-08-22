package com.exchnage.service.currencyexchangedemo;


import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;




@Entity
public class ExchangeValue implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	//@NotNull(message="clint cannot be null")
	@Column(name="client_type")
	private String clienttype;
	
	@Column(name="client_number")
	private String clientnumber;
	
	@Column(name="account_number")
	private String accountnumber;
	
	@Column(name="subaccount_number")
	private String subaccountnumber;
	
	@Column(name="exchange_code")
	private String exchangecode;
	
	@Column(name="product_group_code")
	private String productgroupcode;
	
	@Column(name="symbol")
	private String symbol;
	
	@Column(name="expiry_date")
	private LocalDate expirydate;
	
	@Column(name="quantity_long")
	private long quantitylong;
	
	@Column(name="quantity_short")
	private long quantityshort;

	@Column(name="transaction_date")
	private LocalDate transactiondate;
	
	
	
	/*client type- 4-7   4
	clientnumber  8-11 4
	accountnumber 12-15 4
	subaccountnum  12-15 4

	exchangecode 4 28-31
	prodeuct group code 2 26-27
	symbol 6 32-37
	expiration date 8 ccyymmdd 38-45*/

	

	public long getQuantitylong() {
		return quantitylong;
	}



	public void setQuantitylong(long quantitylong) {
		this.quantitylong = quantitylong;
	}



	public long getQuantityshort() {
		return quantityshort;
	}



	public void setQuantityshort(long quantityshort) {
		this.quantityshort = quantityshort;
	}

	public LocalDate getExpirydate() {
		return expirydate;
	}



	public void setExpirydate(LocalDate expirydate) {
		this.expirydate = expirydate;
	}



	public LocalDate getTransactiondate() {
		return transactiondate;
	}



	public void setTransactiondate(LocalDate transactiondate) {
		this.transactiondate = transactiondate;
	}



	public ExchangeValue() {
		
	}
	
	public ExchangeValue(String clienttype,String clientnumber,String accountnumber,String subaccountnumber,String exchangecode ,String productgroupcode ,String symbol,LocalDate expirydate,long quantityshort,long quantitylong,LocalDate transactiondate) {
		super();
		//this.id=id;
		this.clienttype= clienttype;
		this.clientnumber=clientnumber;
		this.accountnumber=accountnumber;
		this.subaccountnumber=subaccountnumber;
		this.exchangecode= exchangecode;
		this.productgroupcode=productgroupcode;
		this.symbol=symbol;
		this.expirydate=expirydate;
		this.transactiondate=transactiondate;
		this.quantitylong=quantitylong;
		this.quantityshort=quantityshort;
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


	public String getAccountnumber() {
		return accountnumber;
	}


	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}


	public String getSubaccountnumber() {
		return subaccountnumber;
	}


	public void setSubaccountnumber(String subaccountnumber) {
		this.subaccountnumber = subaccountnumber;
	}


	public String getExchangecode() {
		return exchangecode;
	}


	public void setExchangecode(String exchangecode) {
		this.exchangecode = exchangecode;
	}


	public String getProductgroupcode() {
		return productgroupcode;
	}


	public void setProductgroupcode(String productgroupcode) {
		this.productgroupcode = productgroupcode;
	}


	public String getSymbol() {
		return symbol;
	}


	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}




	public void setId(Long id) {
		this.id = id;
	}


	public Long getId() {
		return id;
	}

	
}
