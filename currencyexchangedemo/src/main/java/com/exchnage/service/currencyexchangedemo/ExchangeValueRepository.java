package com.exchnage.service.currencyexchangedemo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface ExchangeValueRepository extends 
		JpaRepository<ExchangeValue, Long>{
	
	@Query("SELECT DISTINCT e.clienttype,e.clientnumber,e.accountnumber,e.subaccountnumber,e.exchangecode,e.productgroupcode,e.symbol,e.expirydate,e.transactiondate from ExchangeValue e ORDER BY e.clientnumber,e.transactiondate ASC")
	List<Object>  findDistinctProductTransaction();
	
	@Query("SELECT SUM(e.quantitylong-e.quantityshort)as totalamount from ExchangeValue e where e.clientnumber=:clientnumber and e.exchangecode=:exchangecode and e.productgroupcode=:productgroupcode and e.symbol=:symbol and e.transactiondate=:transactiondate")
	public long findTotalAmount(@Param("clientnumber")String clientnumber,@Param("exchangecode") String exchangecode,@Param("productgroupcode")String productgroupcode,@Param("symbol")String symbol,@Param("transactiondate")LocalDate transactiondate );
	
}
