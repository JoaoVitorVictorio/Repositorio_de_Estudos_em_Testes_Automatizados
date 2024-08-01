package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import suporte.Utils;

public class LoginPage extends Utils {
    WebDriver driver;
    public LoginPage(WebDriver driver){

        this.driver = driver;
    }

    public void acessarTelaLogin() {
        driver.navigate().to("https://automationexercise.com/login");
    }

    public void novoUsuario() {
        driver.findElement(By.xpath("//*[@id=\'form\']/div/div/div[3]/div/form/input[2]")).sendKeys("João");
        driver.findElement(By.xpath("//*[@id=\'form\']/div/div/div[3]/div/form/input[3]")).sendKeys(geraEmailAleatorio());
    }

    public void clicaBotaoSignup() {
        driver.findElement(By.xpath("//*[@id=\'form\']/div/div/div[3]/div/form/button")).click();
    }

    public void validaPaginaSignup() {
        Assert.assertTrue(driver.getCurrentUrl().equals("https://automationexercise.com/signup"));
    }
}
