package dev.joao.automation.pratice.selenium.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dev.joao.automation.pratice.selenium.pages.CadastroPage;

class CadastroPageTest {
	private CadastroPage cadastroPage;
	private final String URL = "https://automationexercise.com/login";

	@BeforeEach
	void setUp() throws Exception {
		this.cadastroPage = new CadastroPage();
		this.cadastroPage.visit(URL);
	}

	@AfterEach
	void tearDown() throws Exception {
		//this.cadastroPage.quitWebDriver();
	}

	@Test
	void validaEmailDisponivel() {
		//Quando
		this.cadastroPage.registerEmail();
		
		//Então
		String expected = "joao04@gmail.com";
		String actual = this.cadastroPage.getEmailNewAccount();
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	void RealizaCadastro() {
		//Quando
		this.cadastroPage.fillOutForm();
		
		//Então
		String expected = "ACCOUNT CREATED";
		String actual = this.cadastroPage.getWelcomeMessage();
		
		Assertions.assertFalse(this.URL.equals(this.cadastroPage.getCurrentUrl()));
	}

}
