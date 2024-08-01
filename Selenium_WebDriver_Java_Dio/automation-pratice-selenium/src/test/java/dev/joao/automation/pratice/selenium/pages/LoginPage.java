package dev.joao.automation.pratice.selenium.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage{
	//Locators
	private By emailAddressLocator = By.name("email");
	private By passwordLocator = By.name("password");
	private By buttonLoginLocator = By.xpath("//*[@id=\'form\']/div/div/div[1]/div/form/button");
	private By tittleLocator = By.tagName("h1");
	
	
	public void signin() {
		if(super.isDisplayed(emailAddressLocator)) {
			super.type("joaovitor@gmail.com", emailAddressLocator);
			super.type("123456789", passwordLocator);
			super.click(buttonLoginLocator);
		} else {
			System.out.println("Email textbox was not present");
		}
	}
	
	public String getMyAccountMessage() {
		return super.getText(tittleLocator);
	}
}
