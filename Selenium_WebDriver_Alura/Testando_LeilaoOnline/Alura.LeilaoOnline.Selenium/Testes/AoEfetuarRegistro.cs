using OpenQA.Selenium.Chrome;
using System;
using Xunit;
using OpenQA.Selenium;
using Alura.LeilaoOnline.Selenium.PageObjects;

namespace Alura.LeilaoOnline.Selenium.Testes
{
    public class AoEfetuarRegistro: IDisposable
    {
        //Inicializa o navegador e após a liberação do teste efetua o fechamento da página.
        private ChromeDriver driver;

        public AoEfetuarRegistro()
        {
            driver = new ChromeDriver();
        }

        //TearDown (Destruir)
        public void Dispose()
        {
            driver.Quit();
        }

        [Fact]
        public void DadoApenasCliqueSemPreenchimentoNoCampoDeRegistroDevoContinuarNaHome()
        {
            //Arrange - cenário -> Dado que o Chrome foi aberto na página inicial do sistema de Leilão.
            //Preciso localizar os elementos e passar os valores.
            driver.Navigate().GoToUrl("http://localhost:5000/");

            //Botão de Registro
            var BotaoRegistro = driver.FindElement(By.Id("btnRegistro"));


            //Act - método sob teste-> Efetuar Registro
            BotaoRegistro.Click();

            //Assert - Afirmar -> Verificar se fui redirecionado para uma página de agradecimento.
            Assert.Contains("Registre-se para participar dos leilões!", driver.PageSource);
        }


        [Fact]
        public void DadoInformaçãoValidaDevoSerDirecionadoParaPaginaDeAgradecimento()
        {
            //Arrange - cenário -> Dado que o Chrome foi aberto na página inicial do sistema de Leilão.
            //Preciso localizar os elementos e passar os valores.
            driver.Navigate().GoToUrl("http://localhost:5000/");

            //Nome
            var inputNome = driver.FindElement(By.Id("Nome"));
            inputNome.SendKeys("João Vitor");

            //Email
            var inputEmail = driver.FindElement(By.Id("Email"));
            inputEmail.SendKeys("teste@gmail.com");

            //Senha
            var inputPassword = driver.FindElement(By.Id("Password"));
            inputPassword.SendKeys("123456");

            //Confirmação de senha
            var inputConfirmPassword = driver.FindElement(By.Id("ConfirmPassword"));
            inputConfirmPassword.SendKeys("123456");


            //Botão de Registro
            var BotaoRegistro = driver.FindElement(By.Id("btnRegistro"));


            //Act - método sob teste-> Efetuar Registro
            BotaoRegistro.Click();

            //Assert - Afirmar -> verificar se fui redirecionado para uma página de agradecimento.
            Assert.Contains("Obrigado", driver.PageSource);
        }


        //Efetuando teste em Teoria passando vários valores incorretos para tentar registar.
        [Theory]
        [InlineData( "", "teste@gmail.com", "123", "123")] // Testando sem insirir um nome
        [InlineData( "Joao", "teste", "123", "123")] // Testando sem insirir um email válido
        [InlineData( "João", "teste@gmail.com", "", "123")] // Testando sem insirir uma senha
        [InlineData( "João", "teste@gmail.com", "123", "321")] //Testando inserindo uma confirmação de senha inválida

        public void DadoInformaçãoInvalidaDevoContinuarNaHome(
            string nome,
            string email,
            string senha,
            string ConfirmSenha)
        {
            //Arrange - cenário -> Dado que o Chrome foi aberto na página inicial do sistema de Leilão.
            //Preciso localizar os elementos e passar os valores.
            driver.Navigate().GoToUrl("http://localhost:5000/");

            //Nome
            var inputNome = driver.FindElement(By.Id("Nome"));
            inputNome.SendKeys(nome);

            //Email
            var inputEmail = driver.FindElement(By.Id("Email"));
            inputEmail.SendKeys(email);

            //Senha
            var inputPassword = driver.FindElement(By.Id("Password"));
            inputPassword.SendKeys(senha);

            //Confirmação de senha
            var inputConfirmPassword = driver.FindElement(By.Id("ConfirmPassword"));
            inputConfirmPassword.SendKeys(ConfirmSenha);

            //Botão de Registro
            var BotaoRegistro = driver.FindElement(By.Id("btnRegistro"));

            //Act - método sob teste-> Efetuar Registro
            BotaoRegistro.Click();

            //Assert - Afirmar -> Verificar se fui redirecionado para uma página de agradecimento.
            Assert.Contains("Registre-se para participar dos leilões!", driver.PageSource);
        }

        [Fact] //Testando sem o metodo de Page Object
        public void DadoNomeEmBrancoDeveExibirMensagemDeErro()
        {
            //Arrange - cenário -> Dado que o Chrome foi aberto na página inicial do sistema de Leilão.
            //Preciso localizar os elementos e passar os valores.
            driver.Navigate().GoToUrl("http://localhost:5000/");

            //Botão de Registro
            var BotaoRegistro = driver.FindElement(By.Id("btnRegistro"));

            //Act - método sob teste-> método sob teste -> Efetuar o Click
            BotaoRegistro.Click();

            //Assert - Afirmar -> verificar se aparece a mensagem de O campo Nome é obrigatório e devo continuar na home page
            IWebElement elemento = driver.FindElement(By.CssSelector("span#Nome-error"));
            Assert.Equal("The Nome field is required.", elemento.Text);
        }

        [Fact] // As informações contidas neste test estão em RegistroPO.
        public void DadoEmailInvalidoDeveExibirMensagemDeErro()
        {
            //Arrange - cenário -> Dado que o Chrome foi aberto na página inicial do sistema de Leilão.
            //driver.Navigate().GoToUrl("http://localhost:5000/"); //Irei usar o driver importando do RegistroPO
            var registroPO = new RegistroPO(driver);
            registroPO.Visitar();
            registroPO.PreencheFormulario(
                nome: "",
                email: "test",
                senha: "",
                ConfirmSenha: "");

            //email
            //var inputEmail = driver.FindElement(By.Id("Email"));
            //inputEmail.SendKeys("teste"); //Irei usar o driver importando do RegistroPO

            //Acionar o botão de registro
            //var BotaoRegistro = driver.FindElement(By.Id("btnRegistro")); //Irei usar importando do RegistroPO

            //Act - método sob teste-> Efetuar o Clic
            //BotaoRegistro.Click();
            registroPO.SubmeteFormulario();

            //Assert - Afirmar -> verifica se aparece a mensagem de email inválido e devo continuar na home page
            //IWebElement elemento = driver.FindElement(By.CssSelector("span#Email-error")); //Irei usar o IWebElement importando do RegistroPO
            Assert.Equal("Please enter a valid email address.", registroPO.EmailMensagemErro);

        }
    }
}
