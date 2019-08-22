package com.exchnage.service.currencyexchangedemo;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@DataJpaTest
@AutoConfigureTestDatabase()
public class CurrencyExchangeJPATest {
	@Autowired
	private TestExchangeValueRepository repository;
	
	 //@Test
	 public void addExchangeValueTest() throws URISyntaxException, IOException{
		 ExchangeValue exchangevalue=new ExchangeValue("tCL","t1234","0001","003","texchange"
					,"tproduct" ,"tsymbol",LocalDate.now(),1,5,LocalDate.now());
         repository.save(exchangevalue);
         assertTrue(repository.findById(1).getClienttype().toString().equals("tCL"));
   }
}
