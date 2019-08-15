package com.exchnage.service.currencyexchangedemo;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyexchangedemoApplicationTests {
	@Value("${csvfile.path}")
	private String csvFilePath;
	
	@Value("${inputfile.name}")
	private String inputFileName;
	
	@Test
	public void contextLoads() {
	}
	
	  
	 @Test
	    public void testReadFileWithClassLoader()throws URISyntaxException, IOException{
	        ClassLoader classLoader = this.getClass().getClassLoader();
	        File file = new File(classLoader.getResource(inputFileName).getFile());
	        assertTrue(file.exists());
	 
	    }
	 
	 @Test
	 public void testFileCanRead() throws URISyntaxException, IOException{
		   ClassLoader classLoader = this.getClass().getClassLoader();
	        File file = new File(classLoader.getResource(inputFileName).getFile());
	        assertTrue(file.canRead());
	 }
	 
	 @Test
	    public void writeFileExists()throws URISyntaxException, IOException{
	        File file = new File(csvFilePath);
	        assertTrue(file.exists());
    }
	 
	 @Test
	    public void testFileCanWrite()throws  IOException{
	        File file = new File(csvFilePath);
	        assertTrue(file.canWrite());
      }
	 
	 @Test
	 public void fileHasRequiredReadLength() throws URISyntaxException, IOException{
	        
	     Path path = Paths.get(getClass().getClassLoader()
					.getResource(inputFileName).toURI());
		  Stream<String> lines = Files.lines(path); 
		  assertTrue(lines.iterator().next().toString().length()>=129);
   }

}
