package com.exchnage.service.currencyexchangedemo;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TestExchangeValueRepository extends 
		JpaRepository<ExchangeValue, Long>{
	 ExchangeValue findById(long id);
	
}
