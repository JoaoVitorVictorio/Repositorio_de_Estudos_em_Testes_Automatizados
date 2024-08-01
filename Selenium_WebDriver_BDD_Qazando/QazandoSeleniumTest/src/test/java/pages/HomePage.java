package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import suporte.Utils;


public class HomePage extends Utils {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void acessarAplicacao()  {
        driver.navigate().to("https://www.qazando.com.br/curso.html");
    }

    public void scrollDown() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0, 10000)");
        Thread.sleep(2000);
    }

    public void preencheEmail(){
        driver.findElement(By.id("email")).sendKeys("joaovitorv@gmail.com");
    }

    public void clickbotao() {
        driver.findElement(By.xpath("/html/body/center")).click();
        driver.findElement(By.id("button")).click();
    }

    public void VerificarCupomDesconto() {
        Assert.assertTrue(driver.getPageSource().contains("Seu copom é:"));
        Assert.assertTrue(driver.getPageSource().contains("QAZANDO15OFF"));
    }
}
