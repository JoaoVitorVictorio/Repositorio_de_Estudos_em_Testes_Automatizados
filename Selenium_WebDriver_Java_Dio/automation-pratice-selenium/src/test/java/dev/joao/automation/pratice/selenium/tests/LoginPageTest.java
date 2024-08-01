package dev.joao.automation.pratice.selenium.tests;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.joao.automation.pratice.selenium.pages.LoginPage;

class LoginPageTest {
	private LoginPage loginPage;
	private final String URL = "https://automationexercise.com/login";
	

	@BeforeEach
	void setUp() throws Exception {
		loginPage = new LoginPage();
		loginPage.visit(this.URL);
	}

	@AfterEach
	void tearDown() throws Exception {
		loginPage.quitWebDriver();
	}

	@Test
	void test() {
		//Quando
		loginPage.signin();		
		
		//Então
		Assertions.assertTrue(this.loginPage.getMyAccountMessage().equals("AutomationExercise"));
		Assertions.assertFalse(this.loginPage.getCurrentUrl().equals(this.URL));
	}

}
