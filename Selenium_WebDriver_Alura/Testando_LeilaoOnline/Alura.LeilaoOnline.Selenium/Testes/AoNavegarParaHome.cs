using OpenQA.Selenium.Chrome;
using System;
using Xunit;
using OpenQA.Selenium;

namespace Alura.LeilaoOnline.Testes
{
    public class AoNavegarParaHome: IDisposable //Dercarta o projeto ap�s a libera��o
    {
        //Setup (Configura��o) 
        private ChromeDriver driver;
        
        public AoNavegarParaHome()
        {
            driver = new ChromeDriver();
        }

        //TearDown (Destruir)
        public void Dispose()
        {
            driver.Quit();
        } 


        [Fact]
        public void DadoQueOChromeFoiAbertoDeveMostarOTituloLeiloesOnline()
        {
            //Arranje - cen�rio -> Dado que preciso abrir um navegador
            //IWebDriver driver = new ChromeDriver(); Esse c�d ser� chamado no public AoNavegarParaHome()

            //Act - m�todo sob teste-> Preciso acessar o site
            driver.Navigate().GoToUrl("http://localhost:5000");

            //Assert - Afirmar -> Ser direcionado para a p�gina.
            Assert.Contains("Leil�es", driver.Title); // (Espera encontrar a palavra Leil�es no site)

            //Fechamento do navegador sendo chamado do public void Dispose()

        }


        [Fact]
        public void DadoChromeAbertoDeveMostarProximosLeiloes()
        {
            //Arrange - cen�rio ->

            //Act - m�todo sob teste-> Preciso acessar o site Detalhe dos Leil�es
            driver.Navigate().GoToUrl("http://localhost:5000/Home/Detalhes/8");

            //Assert - Afirmar -> Ser direcionado para a pagina e encontrar Proximos Leil�es
            Assert.Contains("Leil�o de Carro 3", driver.PageSource); //PageSource busca no c�digo fonte da p�gina
        }

        [Fact]
        public void DadoChromeAbertoFormRegistroN�oDeveMostarNenhumaMensagemDeErro()
        {
            //Arrange - cen�rio ->

            //Act - m�todo sob teste-> Preciso acessar o site
            driver.Navigate().GoToUrl("http://localhost:5000/");

            //Assert - Afirmar -> N�o encontrar mensagens de erro no formulario de Registro
            var form = driver.FindElement(By.TagName("form")); //buscando no HTML o formulario
            var spans = form.FindElements(By.TagName("span")); //Buscando no formulario algum erro aparente como span
            foreach(var span in spans)
            {
                Assert.True(string.IsNullOrEmpty(span.Text)); //Verifica se de fato n�o tem nenhum erro, mesmo sem aparecer vizualmente
            }
        }
    }
}
