package dev.joao.automation.pratice.selenium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AppTest {
	
	private WebDriver driver;
	
	@Test
	public void helloSelenium() {
		System.getProperty("webdriver.chrome.driver", "drivers.chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://automationexercise.com/");
		
		String currentUrl = driver.getCurrentUrl();
		String expected = "https://automationexercise.com/";
		
		Assertions.assertEquals(expected, currentUrl);
		
		driver.quit();
	}

}
