package com.opencart.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.opencart.base.TestBase;

public class UserRegisteredSuccessfully extends TestBase {

	@Test
	public void userRegisteredSuccessfull() {
		
		driver.findElement(By.cssSelector(or.getProperty("myAccountList"))).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
}
