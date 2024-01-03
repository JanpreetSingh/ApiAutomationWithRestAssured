package com.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;



public class TestBase {
	
	protected Properties prop;
	
	public int RESPONSE_STATUS_CODE_200 = 200;
	public int RESPONSE_STATUS_CODE_201 = 201;
	public int RESPONSE_STATUS_CODE_400 = 400;
	public int RESPONSE_STATUS_CODE_401 =401;
	public int RESPONSE_STATUS_CODE_404 = 404;
	
	public Logger logger;
	
	public TestBase() {
		

			File src = new File("./src/main/java/com/qa/config/config.properties");
			
			try {
				
				FileInputStream fis = new FileInputStream(src);
				
				prop = new Properties();
				prop.load(fis);
				
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			
			
			logger = Logger.getLogger("QARestAPI"); 
			PropertyConfigurator.configure("./log4j.properties");// Path of log4j.properties file.
			

	}

}
