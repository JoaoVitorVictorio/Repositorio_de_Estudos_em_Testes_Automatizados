package dev.joao.automation.pratice.selenium.pages;

import java.time.Duration;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
	private WebDriver driver;
	private WebDriverWait wait;
	private Select select;
	private By dia_nascimento = By.id("days");
	private By mes_nascimento = By.id("months");
	private By ano_nascimento = By.id("years");

	// Construtor da classe que inicializa o driver Chrome e maximiza a janela.
	public BasePage() {
		System.getProperty("webdriver.chrome.driver", "drivers.chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	// Método para navegar até uma URL específica.
	public void visit(String url) {
		this.driver.navigate().to(url);
	}

	// Método para obter a URL atual da página.
	public String getCurrentUrl() {
		return this.driver.getCurrentUrl();
	}

	// Método para fechar o driver do navegador.
	public void quitWebDriver() {
		this.driver.quit();
	}

	// Método para encontrar um elemento na página usando um localizador By.
	public WebElement findElement(By locator) {
		return this.driver.findElement(locator);
	}

	// Método para digitar um valor em um campo de texto identificado por um
	// localizador By.
	public void type(String input, By locator) {
		this.driver.findElement(locator).sendKeys(input);
	}

	// Método para verificar se um elemento identificado por um localizador By está
	// visível na página.
	public boolean isDisplayed(By locator) {
		try {
			return this.driver.findElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	// Método para clicar em um elemento identificado por um localizador By.
	public void click(By locator) {
		this.driver.findElement(locator).click();
	}

	// Método para obter o texto de um elemento identificado por um localizador By.
	public String getText(By locator) {
		return this.driver.findElement(locator).getText();
	}

	// Método para obter o valor de um atributo específico de um elemento
	// identificado por um localizador By.
	public String getTextByAttribute(By locator, String attributeName) {
		return this.driver.findElement(locator).getAttribute(attributeName);
	}

	// Método para aguardar até que um elemento identificado por um localizador By
	// esteja visível na página durante um tempo especificado em segundos. O tempo é
	// definido como um objeto do tipo Duration.
	public WebElement waitVisibilityOfElementLocated(By locator, Duration time) {
		wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// Método para aguardar até que um elemento identificado por um localizador By
	// esteja visível na página.
	public WebElement waitVisibilityOfElementLocated(By locator) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// Método para verificar se uma determinada mensagem ou texto está presente na
	// página atual.
	public Boolean isContainedInPageSource(String message) {
		return driver.getPageSource().contains(message);
	}

	// Método para selecionar uma opção em um elemento do tipo select, identificado
	// por um localizador By, com base no valor da opção.
	public void selectByValue(By locator, String value) {
		select = new Select(findElement(locator));
		select.selectByValue(value);
	}

	// Método para limpar o conteúdo de um campo de texto ou área de texto
	// identificado por um localizador By, removendo todo o texto atual.
	public void clear(By locator) {
		findElement(locator).clear();
	}

	// Método para preencher um formulário de data de nascimento em um site.
	public void preenche_aniversario(Integer day, Integer months, String years) {
		Select select_day = new Select(driver.findElement(dia_nascimento));
		select_day.selectByIndex(day);

		Select select_months = new Select(driver.findElement(mes_nascimento));
		select_months.selectByIndex(months);

		Select select_years = new Select(driver.findElement(ano_nascimento));
		select_years.selectByValue(years);
	}
}
