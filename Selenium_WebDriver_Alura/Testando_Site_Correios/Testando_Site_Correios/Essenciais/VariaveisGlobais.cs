using OpenQA.Selenium;

namespace Testando_Site_Correios.Essenciais
{
    public class VariaveisGlobais
    {
        // Define 'driver' como gatilho para os WebElementos
        public IWebDriver driver;

        // Define 'Fechar o navegador após finalizar o teste' como padrão
        public bool driverQuit = true;

        // Habilita | Desabilita modo Headless
        public bool headlessTest = false;

        public string exceptionMsg = "\nSelenium Message Error: ";
    }
}
