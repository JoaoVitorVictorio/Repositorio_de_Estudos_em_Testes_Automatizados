package suporte;

import runner.RunCucumberTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;


public class Utils extends RunCucumberTest {
    public void EsperarElementoEstarPresente(By element, int tempo){
        WebDriverWait wait = new WebDriverWait(driver, tempo);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public String geraEmailAleatorio(){
        String email_init = "joaovitor_";
        String email_final = "@gmail.com";

        Random random  = new Random();
        int minimo = 1;
        int maximo = 9999;
        int resultado = random.nextInt(maximo-minimo) + minimo;

        return email_init + resultado + email_final;
    }
}
