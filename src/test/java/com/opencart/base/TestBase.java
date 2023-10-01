package com.opencart.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
		
		if (config.getProperty("browser").equals("firefox")) {
			
			driver = new FirefoxDriver();
		} else if (config.getProperty("browser").equals("chrome")) {
			
			driver = new ChromeDriver();
		} else if (config.getProperty("browser").equals("edge")) {
			
			driver = new EdgeDriver();
		}
		
		driver.get(config.getProperty("testsiteurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts()
		.implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicitwait"))));
	}
	
	public void tearDown() {
		if (driver != null)
			driver.quit();
	}
}
