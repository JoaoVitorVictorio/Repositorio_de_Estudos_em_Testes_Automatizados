package steps;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import pages.LoginPage;
import runner.RunCucumberTest;

public class LoginSteps extends RunCucumberTest {

    LoginPage loginPage = new LoginPage(driver);

    @Dado("^que estou na tela de login$")
    public void que_estou_na_tela_de_login()  {

        loginPage.acessarTelaLogin();
    }

    @E("^acesso o para cadastrar usuário$")
    public void acesso_o_para_cadastrar_usuário() {
        loginPage.novoUsuario();
        loginPage.clicaBotaoSignup();
        loginPage.validaPaginaSignup();
    }
}


