package com.opencart.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;

public class TestBase {

	/*
	 * 
	 * WebDriver
	 * Properties
	 * Logs
	 * Reports
	 * Excel
	 * Mail
	 * 
	 */
	
	
	public static WebDriver driver;
	public static Properties config;
	public static Properties or;
	public static FileInputStream fis;
	
	
	@BeforeSuite
	public void setUp() {
	
		if (driver == null) {
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir") +
						"/src/test/resources/properties/Config.properties");
				config.load(fis);
			
				fis = new FileInputStream(System.getProperty("user.dir") 
						+ "/src/test/resources/properties/OR.properties");
				or.load(fis);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
	}
	
	public void tearDown() {
		
		
	}
}
