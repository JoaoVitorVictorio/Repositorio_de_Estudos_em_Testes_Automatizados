using OpenQA.Selenium.Chrome;
using System;
using Xunit;
using OpenQA.Selenium;

namespace Alura.LeilaoOnline.Testes
{
    public class AoNavegarParaHome: IDisposable //Dercarta o projeto após a liberação
    {
        //Setup (Configuração) 
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
            //Arranje - cenário -> Dado que preciso abrir um navegador
            //IWebDriver driver = new ChromeDriver(); Esse cód será chamado no public AoNavegarParaHome()

            //Act - método sob teste-> Preciso acessar o site
            driver.Navigate().GoToUrl("http://localhost:5000");

            //Assert - Afirmar -> Ser direcionado para a página.
            Assert.Contains("Leilões", driver.Title); // (Espera encontrar a palavra Leilões no site)

            //Fechamento do navegador sendo chamado do public void Dispose()

        }


        [Fact]
        public void DadoChromeAbertoDeveMostarProximosLeiloes()
        {
            //Arrange - cenário ->

            //Act - método sob teste-> Preciso acessar o site Detalhe dos Leilões
            driver.Navigate().GoToUrl("http://localhost:5000/Home/Detalhes/8");

            //Assert - Afirmar -> Ser direcionado para a pagina e encontrar Proximos Leilões
            Assert.Contains("Leilão de Carro 3", driver.PageSource); //PageSource busca no código fonte da página
        }

        [Fact]
        public void DadoChromeAbertoFormRegistroNãoDeveMostarNenhumaMensagemDeErro()
        {
            //Arrange - cenário ->

            //Act - método sob teste-> Preciso acessar o site
            driver.Navigate().GoToUrl("http://localhost:5000/");

            //Assert - Afirmar -> Não encontrar mensagens de erro no formulario de Registro
            var form = driver.FindElement(By.TagName("form")); //buscando no HTML o formulario
            var spans = form.FindElements(By.TagName("span")); //Buscando no formulario algum erro aparente como span
            foreach(var span in spans)
            {
                Assert.True(string.IsNullOrEmpty(span.Text)); //Verifica se de fato não tem nenhum erro, mesmo sem aparecer vizualmente
            }
        }
    }
}
