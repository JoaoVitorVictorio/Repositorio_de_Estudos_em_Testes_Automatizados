package dev.joao.automation.pratice.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


class LoginTest {
	private WebDriver driver;
	@BeforeEach
	void setUp() throws Exception {
		System.getProperty("webdriver.chrome.driver", "drivers.chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://automationexercise.com/login");
	}

	@AfterEach
	void tearDown() throws Exception {
		driver.quit();
	}
	

	@Test
	void test() {
		//Escreve o e-mail no campo e-mail
		WebElement emailAddress = driver.findElement(By.name("email"));
		emailAddress.sendKeys("joaovitor@gmail.com");
		
		//Escreve a senha no campo password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("123456789");
		
		//Clica no botão login
		WebElement buttonLogin = driver.findElement(By.xpath("//*[@id=\'form\']/div/div/div[1]/div/form/button"));
		buttonLogin.click();
		
		//Assetiva que possui o texto AutomationExercise na home page de login
		WebElement tagHomePageMyAccount = driver.findElement(By.tagName("h1"));
		tagHomePageMyAccount.getText().contains("AutomationExercise");
	}

}
