package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import suporte.Utils;

public class CadastroPage extends Utils {
    WebDriver driver;
    private By generoM = By.id("id_gender1");
    private By generoF = By.id("id_gender2");
    private By Nome = By.id("name");
    private By Senha  = By.id("password");
    private By dia_nascimento = By.id("days");
    private By mes_nascimento = By.id("months");
    private By ano_nascimento = By.id("years");
    private By primeiro_nome = By.id("first_name");
    private By Sobrenome = By.id("last_name");
    private By Empresa = By.id("company");
    private By Endereco = By.id("address1");
    private By Pais = By.id("country");
    private By Estado = By.id("state");
    private By Cidade = By.id("city");
    private By CEP = By.id("zipcode");
    private By Celular = By.id("mobile_number");



    public CadastroPage(WebDriver driver) {

        this.driver = driver;
    }

    public void selecione_genero(Integer type){
        EsperarElementoEstarPresente(generoM, 15);
        if (type == 1){
            driver.findElement(generoM).click();
        }else if (type == 2) {
            driver.findElement(generoF).click();
        }
    }

    public void preenche_nome(String nome){
        driver.findElement(Nome).sendKeys(nome);
    }

    public void preenche_email(String email){

    }

    public void preenche_senha(String senha){
        driver.findElement(Senha).sendKeys(senha);
    }

    public void preenche_aniversario(Integer day, Integer months, String years){
        Select select_day = new Select(driver.findElement(dia_nascimento));
        select_day.selectByIndex(day);

        Select select_months = new Select(driver.findElement(mes_nascimento));
        select_months.selectByIndex(months);

        Select select_years = new Select(driver.findElement(ano_nascimento));
        select_years.selectByValue(years);
    }

    public void preenche_primeiro_nome(String nome){
        driver.findElement(primeiro_nome).sendKeys(nome);
    }

    public void preenche_sobrenome(String sobrenome){
        driver.findElement(Sobrenome).sendKeys(sobrenome);
    }

    public void preenche_empresa(String empresa){
        driver.findElement(Empresa).sendKeys(empresa);
    }

    public void preenche_endereco(String endereco){
        driver.findElement(Endereco).sendKeys(endereco);
    }

    public void preenche_pais(String pais){
        Select select_country = new Select(driver.findElement(Pais));
        select_country.selectByValue(pais);
    }

    public void preenche_estado(String estado){
        driver.findElement(Estado).sendKeys(estado);
    }

    public void preenche_cidade(String cidade){
        driver.findElement(Cidade).sendKeys(cidade);
    }

    public void preenche_Cep(String cep){
        driver.findElement(CEP).sendKeys(cep);
    }

    public void preenche_celular(String celular){
        driver.findElement(Celular).sendKeys(celular);
    }

    public void clicaEmCriarConta() {
        driver.findElement(By.xpath("//*[@id=\'form\']/div/div/div/div[1]/form/button")).click();
    }

    public void validaCadastroEfetuado() {
        Assert.assertTrue(driver.getPageSource().contains("Account Created!"));
        Assert.assertTrue(driver.getCurrentUrl().equals("https://automationexercise.com/account_created"));
    }
}
