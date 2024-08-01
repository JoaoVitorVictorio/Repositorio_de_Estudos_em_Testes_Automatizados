using NUnit.Framework;
using OpenQA.Selenium.Chrome;
using System;
using Testando_Site_Correios.Essenciais;

namespace Testando_Site_Correios.Essencial
{
    public class Começo : DSL
    {
        #region Código abre Navegador
        private void AbreNavegador() // Configuração de abertura do navegador
        {
            var headlessMode = new ChromeOptions();
            headlessMode.AddArgument("window-size=1366x768");
            headlessMode.AddArgument("disk-cache-size=0");
            headlessMode.AddArgument("headless");

            var devMode = new ChromeOptions();
            devMode.AddArgument("disk-cache-size=0");
            devMode.AddArgument("start-maximized");

            if (headlessTest)
            {
                driver = new ChromeDriver(headlessMode); // Modo Headless executa os testes sem abrir o navegador
            }
            else
            {
                driver = new ChromeDriver(devMode); driverQuit = true;
            }
            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(5);
        }
        #endregion

        #region Setup
        [SetUp]
        public void InicioTeste()
        {
            AbreNavegador();
            driver.Navigate().GoToUrl("https://buscacepinter.correios.com.br/app/endereco/index.php"); // Acessa o site para executar o projeto de teste
        }
        #endregion

        #region TearDown
        [TearDown]
        public void FimDoTeste()
        {
            if (driverQuit) driver.Quit(); //Fecha o navegador após finalizar o teste
        }
        #endregion
    }


    public class AluraPic : DSL
    {
        #region Código abre Navegador
        private void AbreNavegador() // Configuração de abertura do navegador
        {
            var headlessMode = new ChromeOptions();
            headlessMode.AddArgument("window-size=1366x768");
            headlessMode.AddArgument("disk-cache-size=0");
            headlessMode.AddArgument("headless");

            var devMode = new ChromeOptions();
            devMode.AddArgument("disk-cache-size=0");
            devMode.AddArgument("start-maximized");

            if (headlessTest)
            {
                driver = new ChromeDriver(headlessMode); // Modo Headless executa os testes sem abrir o navegador
            }
            else
            {
                driver = new ChromeDriver(devMode); driverQuit = true;
            }
            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(5);
        }
        #endregion

        #region Setup
        [SetUp]
        public void InicioTeste()
        {
            AbreNavegador();
            driver.Navigate().GoToUrl("https://alura-fotos.herokuapp.com/#/home"); // Acessa o site para executar o projeto de teste
        }
        #endregion

        #region TearDown
        [TearDown]
        public void FimDoTeste()
        {
            if (driverQuit) driver.Quit(); //Fecha o navegador após finalizar o teste
        }
        #endregion
    }
}