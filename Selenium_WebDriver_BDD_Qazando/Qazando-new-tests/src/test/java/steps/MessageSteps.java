package steps;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pages.MessagePage;
import runner.RunCucumberTest;

public class MessageSteps extends RunCucumberTest {

    MessagePage messagePage = new MessagePage(driver);

    @Dado("^que estou na tela de mensagens$")
    public void que_estou_na_tela_de_mensagens() {
        messagePage.site();
    }

    @E("^preencho todos os campos$")
    public void preencho_todos_os_campos() {
        messagePage.preenche_nome("João");
        messagePage.preenche_email("joao@gmail.com");
        messagePage.preenche_assunto("Envio de comprovante");
        messagePage.preenche_mensagem("Segue em anexo o comprovante conforme solicitado");
        messagePage.envia_arquivo("C:/Users/JN/Documents/MEUS PROJETOS/Projetos/Pasta-de-estudo/Testes-automatizados/Selenium_BDD/Qazando-new-tests/comprovante.png");
    }

    @Quando("^eu clico em enviar mensagem$")
    public void eu_clico_em_enviar_mensagem()  {
        messagePage.clicaEnviar();
    }

    @Então("^vejo feedback de mensagem enviada com sucesso$")
    public void vejo_feedback_de_mensagem_enviada_com_sucesso() {
       messagePage.validaEnvio();
    }
}
