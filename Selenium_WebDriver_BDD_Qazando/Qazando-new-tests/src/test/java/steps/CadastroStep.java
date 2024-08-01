package steps;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.openqa.selenium.By;
import pages.CadastroPage;
import runner.RunCucumberTest;
import suporte.Utils;

public class CadastroStep extends Utils {

    CadastroPage cadastroPage = new CadastroPage(driver);

    @Quando("^eu preencho o formulario de cadastro$")
    public void eu_preencho_o_formulario_de_cadastro() {
        cadastroPage.selecione_genero(1);
        cadastroPage.preenche_email(geraEmailAleatorio());
        cadastroPage.preenche_senha("123456789");
        cadastroPage.preenche_aniversario(13,10,"1997");
        cadastroPage.preenche_primeiro_nome("João Victor");
        cadastroPage.preenche_sobrenome("Vettore");
        cadastroPage.preenche_endereco("R. dos testes, 987");
        cadastroPage.preenche_pais("United States");
        cadastroPage.preenche_estado("Florida");
        cadastroPage.preenche_cidade("Miami");
        cadastroPage.preenche_Cep("86056650");
        cadastroPage.preenche_celular("4399108067");
    }

    @E("^clico em registar$")
    public void clico_em_registar() {
        cadastroPage.clicaEmCriarConta();
    }

    @Então("^vejo a mensagem de cadastro realizado com sucesso$")
    public void vejo_a_mensagem_de_cadastro_realizado_com_sucesso() {
        cadastroPage.validaCadastroEfetuado();
    }
}
