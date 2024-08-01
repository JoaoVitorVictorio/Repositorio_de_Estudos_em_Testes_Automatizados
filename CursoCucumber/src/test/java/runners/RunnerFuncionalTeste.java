package runners;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/features/inserir_conta.feature",
		glue = "steps",
	    tags = {"@funcionais"},
	    plugin = {"pretty", "html:target/report-html", "json:target/report.json"},
		monochrome = false,
		snippets = SnippetType.CAMELCASE,
		dryRun = false,
		strict = false
		)
public class RunnerFuncionalTeste {
	@BeforeClass
	public static void restauraTeste() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://seubarriga.wcaquino.me/login");
		driver.findElement(By.id("email")).sendKeys("joaovictorio@gmail.com");
		driver.findElement(By.id("senha")).sendKeys("123456789");
		driver.findElement(By.xpath("//button[.=contains(text(),'Entrar')]")).click();
		driver.findElement(By.xpath("//a[.=contains(text(),'reset')]")).click();
		System.out.println("Banco restaurado com sucesso!");
		driver.close();
	}
}
