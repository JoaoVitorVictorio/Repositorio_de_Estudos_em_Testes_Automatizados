using NUnit.Framework;
using OpenQA.Selenium;
using Testando_Site_Correios.Essencial;

namespace Testando_Site_Correios.Testes
{
    class BuscaCep : Começo // Busca os dados do projeto Começo
    {
        [Test]
        public void Teste() // Teste de buscar um CEP
        {
            driver.FindElement(By.Id("endereco")).SendKeys("86056670");
            driver.FindElement(By.Id("btn_pesquisar")).Click();
            //Assert.That(driver.FindElement(By.XPath("//*[@id=\'resultado-DNEC\']/tbody/tr/td[1]")).Text.Contains("Rua Olympio Theodoro"), Is.True); // Metodo Selenium
            Assert.That(driver.FindElement(By.XPath("//*[@id=\'resultado-DNEC\']/tbody/tr/td[1]")).Text, Does.Contain("Rua Olympio Theodoro")); // Metodo NUnit
        }
    }
}
