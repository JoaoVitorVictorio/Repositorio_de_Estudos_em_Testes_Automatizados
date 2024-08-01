package steps;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class InserirContasSteps {
	private WebDriver driver;
	
	@Dado("que estou acessando a aplicação")
	public void que_estou_acessando_a_aplicação() {
		driver = new ChromeDriver();
		driver.get("https://seubarriga.wcaquino.me/login");
	}

	@Quando("informo o usuário {string}")
	public void informo_o_usuário(String email) {
		driver.findElement(By.id("email")).sendKeys(email);
	}

	@Quando("a senha {string}")
	public void a_senha(String senha) {
		driver.findElement(By.id("senha")).sendKeys(senha);
	}

	@Quando("seleciono entrar")
	public void seleciono_entrar() {
		driver.findElement(By.xpath("//button[.=contains(text(),'Entrar')]")).click();
	}

	@Então("visualizo a página inicial")
	public void visualizo_a_página_inicial() {
		String texto = driver.findElement(By.xpath("//*[@class='alert alert-success']")).getText();
		Assert.assertEquals("Bem vindo, joao!", texto);
	}

	@Quando("seleciono Contas")
	public void seleciono_Contas() {
		driver.findElement(By.xpath("//*[@class='dropdown-toggle']")).click();
		
	}

	@Quando("seleciono Adicionar")
	public void seleciono_Adicionar() {
		driver.findElement(By.xpath("//*[@id='navbar']//a[contains(text(),'Adicionar')]")).click();
	}

	@Quando("informo a conta {string}")
	public void informo_a_conta(String conta) {
		driver.findElement(By.id("nome")).sendKeys(conta);
	}

	@Quando("seleciono Salvar")
	public void seleciono_Salvar() {
		driver.findElement(By.xpath("//button[@class = 'btn btn-primary']")).click();
	}

	@Então("a conta é inserida com sucesso")
	public void a_conta_é_inserida_com_sucesso() {
		String salvoComSucesso = driver.findElement(By.xpath("//div[@class = 'alert alert-success']")).getText();
		Assert.assertEquals("Conta adicionada com sucesso!", salvoComSucesso);
	}

	@Então("sou notificado que o nome da conta é obrigatório")
	public void sou_notificado_que_o_nome_da_conta_é_obrigatório() {
		String nomeConta = driver.findElement(By.xpath("//div[@class = 'alert alert-danger']")).getText();
		Assert.assertEquals("Informe o nome da conta", nomeConta);
	}

	@Então("sou notificado que já existe uma conta com esse nome")
	public void sou_notificado_que_já_existe_uma_conta_com_esse_nome() {
		String contaExistente = driver.findElement(By.xpath("//div[@class = 'alert alert-danger']")).getText();
		Assert.assertEquals("Já existe uma conta com esse nome!", contaExistente);
	}
	
	@Before
	public void inicio(){
		System.out.println("Teste iniciado");		
	}
	
	@After(order = 1, value = "@funcionais")
	public void screenshot(Scenario cenario) {
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File("target/screenshot/"+cenario.getId()+".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@After(order = 0, value = "@funcionais")
	public void fecharBrowser() {
		driver.close();
		System.out.println("Teste encerrado");
	}
}
