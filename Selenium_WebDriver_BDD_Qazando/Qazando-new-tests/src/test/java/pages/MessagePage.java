package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import suporte.Utils;

public class MessagePage extends Utils {
    WebDriver driver;

    private By Nome = By.xpath("//*[@id=\'contact-us-form\']/div[1]/input");
    private By Email = By.xpath("//*[@id=\'contact-us-form\']/div[2]/input");
    private By Assunto = By.xpath("//*[@id=\'contact-us-form\']/div[3]/input");
    private By Mensagem = By.id("message");
    private By Envia_Arquivo = By.xpath("//*[@id=\'contact-us-form\']/div[5]/input");

    public MessagePage(WebDriver driver) {
        this.driver = driver;
    }

    public void site (){
        driver.navigate().to("https://automationexercise.com/contact_us");
        EsperarElementoEstarPresente(Nome, 20);
    }

    public void preenche_nome(String nome){
        driver.findElement(Nome).sendKeys(nome);
    }

    public void preenche_email(String email){
        driver.findElement(Email).sendKeys(email);
    }

    public void preenche_assunto(String assunto){
        driver.findElement(Assunto).sendKeys(assunto);
    }

    public void preenche_mensagem(String mensagem){
        driver.findElement(Mensagem).sendKeys(mensagem);
    }

    public void envia_arquivo(String file){
        driver.findElement(Envia_Arquivo).sendKeys(file);
    }

    public void clicaEnviar(){
        driver.findElement(By.cssSelector("#contact-us-form > div:nth-child(7) > input")).click();
    }

    public void validaEnvio(){
        //Este método é responsável por validar o envio de detalhes.
        // Primeiramente, ele tenta aceitar o pop-up que pode aparecer na tela.
        // Caso não haja nenhum pop-up, ocorrerá uma exceção NoAlertPresentException e será tratada dentro do bloco catch.
        try {
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            // handle exception
        }
        Assert.assertTrue(driver.getPageSource().contains("Success! Your details have been submitted successfully."));
    }
}
