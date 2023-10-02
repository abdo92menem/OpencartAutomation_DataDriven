package com.opencart.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
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
	public static Properties config = new Properties();
	public static Properties or = new Properties();
	public static FileInputStream fis;
	public static Logger logger = LogManager.getLogger("devpinoyLogger");
	
	@BeforeSuite
	public void setUp() {
	
		if (driver == null) {
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir") +
						"/src/test/resources/properties/Config.properties");
				config.load(fis);
				logger.debug("Config File Loaded !!");
			
				fis = new FileInputStream(System.getProperty("user.dir") 
						+ "/src/test/resources/properties/OR.properties");
				or.load(fis);
				logger.debug("OR File Loaded !!");

			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		if (config.getProperty("browser").equals("firefox")) {
			
			driver = new FirefoxDriver();
			logger.debug("Firefox Browser is launched !!");
			
		} else if (config.getProperty("browser").equals("chrome")) {
			
			driver = new ChromeDriver();
			logger.debug("Chrome Browser is launched !!");
			
		} else if (config.getProperty("browser").equals("edge")) {
			
			driver = new EdgeDriver();
			logger.debug("Edge Browser is launched !!");
			
		}
		
		driver.get(config.getProperty("testsiteurl"));
		logger.debug("Navigating to " + config.getProperty("testsiteurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts()
		.implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicitwait"))));
	}
	
	@AfterSuite
	public void tearDown() {
		if (driver != null)
			driver.quit();
		
		logger.debug("Test Execution Completed !!");
	}
}
