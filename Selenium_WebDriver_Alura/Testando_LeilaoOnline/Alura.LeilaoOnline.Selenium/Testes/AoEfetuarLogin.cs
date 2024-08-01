using Alura.LeilaoOnline.Selenium.PageObjects;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using System;
using System.Collections.Generic;
using System.Text;
using Xunit;

namespace Alura.LeilaoOnline.Selenium.Testes
{
    public class AoEfetuarLogin: IDisposable
    {
        private IWebDriver driver;

        public AoEfetuarLogin()
        {
            driver = new ChromeDriver();
        }

        public void Dispose()
        {
            driver.Quit();
        }

        [Fact]
        public void DadoCredenciaisValidasDeveRedirecionarPaginaDeLogado()
        {
            //Arrange - cenário -> Abrir navegador, visitar a pág, Preencher o formulario
            var LoginPO = new LoginPO(driver);
            LoginPO.Visitar();
            LoginPO.PreencherFormulario("fulano@example.org", "123");

            //Act - Ação -> Submeter o Formulario
            LoginPO.SubmeteFormulario();

            //Assert - Afirmar -> Que estarei Logado
            Assert.Contains("Dashboard", driver.Title);
        }

        [Fact]
        public void DadoCredenciaisInvalidasPermanecerNaPaginaDeLogin()
        {
            //Arrange - cenário -> Abrir navegador, visitar a pág, Preencher o formulario com dados invalidos
            var LoginPO = new LoginPO(driver);
            LoginPO.Visitar();
            LoginPO.PreencherFormulario("Ciclano@example.com", "321");

            //Act - Ação -> Submeter o Formulario
            LoginPO.SubmeteFormulario();

            //Assert - Afirmar -> Que não conseguirei logar
            Assert.Contains("Login", driver.PageSource);
        }
    }
}
