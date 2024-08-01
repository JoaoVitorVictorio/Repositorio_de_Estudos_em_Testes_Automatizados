package dev.joao.automation.pratice.selenium.pages;

import org.openqa.selenium.By;

public class CadastroPage extends BasePage {
	// Locators
	private By nameLocator = By.xpath("//*[@id=\'form\']/div/div/div[3]/div/form/input[2]");
	private By emailAddressLocator = By.xpath("//*[@id=\'form\']/div/div/div[3]/div/form/input[3]");
	private By buttonSignup = By.xpath("//*[@id=\'form\']/div/div/div[3]/div/form/button");

	// Locators ENTER ACCOUNT INFORMATION
	private By emailLocator = By.id("email");
	private By alertAdditionalInfomationLocator = By.xpath("//*[@id=\'form\']/div/div/div/div/form/p[4]/label/span");
	private By titleLocator = By.id("id_gender1");
	private By passwordLocator = By.id("password");
	private By newsletterLocator = By.id("newsletter");
	private By receiveLocator = By.id("optin");

	// Locators ADDRESS INFORMATION
	private By firstNameLocator = By.id("first_name");
	private By lastNameLocator = By.id("last_name");
	private By companyLocator = By.id("company");
	private By addressLocator = By.id("address1");
	private By countryLocator = By.id("country");
	private By stateLocator = By.id("state");
	private By cityLocator = By.id("city");
	private By zipcodeLocator = By.id("zipcode");
	private By mobileNumberLocator = By.id("mobile_number");
	private By buttonCreateAccountLocator = By.xpath("//*[@id=\'form\']/div/div/div/div/form/button");
	private By accountCreate = By.cssSelector("#form > div > div > div > h2 > b");

	public void registerEmail() {
		if (super.isDisplayed(emailAddressLocator)) {
			super.type("Joao", nameLocator);
			super.type("joao04@gmail.com", emailAddressLocator);
			super.click(buttonSignup);
		} else {
			System.out.println("Campo email não localizado");
		}
	}

	public String getEmailNewAccount() {
		super.waitVisibilityOfElementLocated(alertAdditionalInfomationLocator);
		return super.getTextByAttribute(emailLocator, "value");
	}

	public void fillOutForm() {
		this.registerEmail();
		super.click(titleLocator);
		super.type("123456789", passwordLocator);
		super.preenche_aniversario(03,10,"1997");
		super.click(newsletterLocator);
		super.click(receiveLocator);
		super.type("Joao", firstNameLocator);
		super.type("Vitor", lastNameLocator);
		super.type("Scot", companyLocator);
		super.type("Rua do teste", addressLocator);
		selectByValue(countryLocator, "United States");
		super.type("Texas", stateLocator);
		super.type("El Paso", cityLocator);
		super.type("86056000", zipcodeLocator);
		super.type("99999-9999", mobileNumberLocator);
		super.click(buttonCreateAccountLocator);
	}
	
	public String getWelcomeMessage() {
		super.waitVisibilityOfElementLocated(accountCreate);
		return super.getText(accountCreate);
	}
}
