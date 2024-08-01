using OpenQA.Selenium;
using Alura.LeilaoOnline.Selenium.Testes;
using System;

namespace Alura.LeilaoOnline.Selenium.PageObjects
{
    public class RegistroPO
    {
        private IWebDriver driver;
        private By byFormRegistro;
        private By byInputNome;
        private By byInputEmail;
        private By byInputSenha;
        private By byInputConfirmSenha;
        private By byBotaoRegistro;
        private By bySpanErroNomeEmail;

        public string EmailMensagemErro => driver.FindElement(bySpanErroNomeEmail).Text;

        public RegistroPO(IWebDriver driver)
        {
            this.driver = driver;
            byFormRegistro = By.TagName("form");
            byInputNome = By.Id("Nome");
            byInputEmail = By.Id("Email");
            byInputSenha = By.Id("Password");
            byInputConfirmSenha = By.Id("ConfirmPassword");
            byBotaoRegistro = By.Id("btnRegistro");
            bySpanErroNomeEmail = By.CssSelector("span#Email-error");
        }

        public void Visitar()
        {
            driver.Navigate().GoToUrl("http://localhost:5000/");
        }

        public void PreencheFormulario(
            string nome,
            string email,
            string senha,
            string ConfirmSenha
        )
        {
            driver.FindElement(byInputNome).SendKeys(nome);
            driver.FindElement(byInputEmail).SendKeys(email);
            driver.FindElement(byInputSenha).SendKeys(senha);
            driver.FindElement(byInputConfirmSenha).SendKeys(ConfirmSenha);
        }

        public void SubmeteFormulario()
        {
            driver.FindElement(byBotaoRegistro).Click();
        }
    }
}
