package com.exchnage.service.currencyexchangedemo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CurrencyExchangeController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ExchangeValueRepository repository;
	
	@Value("${inputfile.name}")
	private String inputFileName;
	
	@Value("${csvfile.path}")
	private String csvFilePath;
	
	@GetMapping("/currency-exchange/readinput")
	public String retrieveExchangeValue
		() throws URISyntaxException, IOException{
			logger.info("CurrencyExchangeController:Start");
			try {
				readInputFile();
		        writeCSVFile();
			}catch (IOException ex) {
				logger.error("IOException"+ ex.toString());
				return "exception occured "+ex.toString();
		    }
			catch(URISyntaxException uex)
			{
			   logger.error("c"+ uex.toString());
			   return "exception occured "+uex.toString();
			}
			catch(Exception e)
			{
				logger.error("Unknon Exception retrieveExchangeValue"+e.toString());
				return "exception occured "+e.toString();
			}
       
		return "csv file has been successfully generated at "+csvFilePath;
	}
	
	 public void readInputFile() throws URISyntaxException, IOException{
		 logger.info("calling readInputFile");
		 Path path = Paths.get(getClass().getClassLoader()
					.getResource(inputFileName).toURI());
		 logger.info("CurrencyExchangeController:calling readInputFile");
		 logger.info("calling readInputFile:Filename"+inputFileName);
				 try (Stream<String> lines = Files.lines(path);) {
					     lines.forEach(s -> {	
					       	String clientType=s.substring(4-1, 7).trim();
							String clientnumber =s.substring(8-1,11).trim();
							String accountnumber =s.substring(12-1,15).trim();
							String subaccountnum  =s.substring(16-1,19).trim();
			  				String exchangecode =s.substring(28-1,31).trim();
							String prodeuctgroupcode =s.substring( 26-1,27).trim();
							String symbol=s.substring( 32-1,37).trim();
							long quantitylong=Long.parseLong(s.substring( 53-1,62).trim());
							long quantityshort=Long.parseLong(s.substring( 64-1,73).trim());
							String expdatestr=s.substring( 38-1,45).trim();
							String transdatestr=s.substring(122-1,129).trim();
							if(expdatestr==null || transdatestr==null || expdatestr.isEmpty() || transdatestr.isEmpty()) 
								throw new NullPointerException("Dates Empty");
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd", Locale.ENGLISH);
							LocalDate expdate = LocalDate.parse(expdatestr, formatter);
							LocalDate transdate = LocalDate.parse(transdatestr, formatter);
							logger.info("readInputFile:transactioninfo"+"clientType"+clientType+"clientnumber"+clientnumber+"transactiondate"+transdatestr+"expirydate"+expdatestr);
							ExchangeValue exchangevalue=new ExchangeValue(clientType,clientnumber,accountnumber,subaccountnum,exchangecode
									,prodeuctgroupcode ,symbol,expdate,quantityshort,quantitylong,transdate );
	                        repository.save(exchangevalue);
                       });
	     	        } catch (IOException ex) {
						logger.error("readInputFile:exception"+ ex.toString());
                        throw ex;
				        }
			  }
	 
	 public void writeCSVFile() throws IOException
	 {
 	       File report = new File(csvFilePath);
		   report.createNewFile();
		  try (FileWriter csvWriter=new FileWriter(report))
		  {
			csvWriter.append("Client_Information,"+"Product_Information,"+"Total_Transaction_Amount,"+"Transaction_Date\n");
			List <Object> distinctProduct=repository.findDistinctProductTransaction();
			distinctProduct.forEach( object -> {
				Object obj[]=(Object[])object;
				LocalDate transactionDate=(LocalDate)obj[8];
					try {
						csvWriter.append(obj[0].toString()+"|"+obj[1].toString()+"|"+obj[2].toString()+"|"+obj[3].toString()+",");
						csvWriter.append(obj[4].toString()+"|"+obj[5].toString()+"|"+obj[6].toString()+"|"+obj[7].toString()+",");
	   			 	    long totalamount=repository.findTotalAmount(obj[1].toString(),obj[4].toString() , obj[5].toString(), obj[6].toString(), transactionDate);
					    csvWriter.append(totalamount+","+obj[8].toString()+"\n");
					    logger.info(obj[0].toString()+obj[1].toString()+obj[2].toString()+obj[3].toString()+obj[4].toString()+obj[5].toString()+obj[6].toString()+obj[7].toString()+obj[8].toString()+totalamount);
	
					} catch (IOException e) {
						logger.error("writeCSVFile:exception"+ e.toString());
					}
       		});
		
		    csvWriter.flush(); 
		 }
		 catch(Exception e1) {
			 logger.error("writeCSVFile:exception"+ e1.toString());
		 }
	 }
	
}
