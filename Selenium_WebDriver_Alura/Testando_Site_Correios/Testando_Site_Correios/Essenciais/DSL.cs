using NUnit.Framework;
using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;
using System;
using System.IO;
using System.Runtime.InteropServices;
using System.Threading;

namespace Testando_Site_Correios.Essenciais
{
    public class DSL : VariaveisGlobais // DSL é onde eu vou criar as funçoes e os valores a serem atribuidos
    {

        #region Funções de Interação
        public void PreencheCampos(string xpath, string valor, [Optional] string descrição) // Irá ser utilizado para inserir dados em um determinado campo
        {
            try
            {
                driver.FindElement(By.XPath(xpath)).SendKeys(valor);
                if (descrição != null ) Console.WriteLine("Preencheu " + descrição);
            }
            catch (Exception ex)
            {
                if (descrição != null) Console.WriteLine("Erro ao preencher " + descrição + exceptionMsg + ex.Message); Assert.Fail();
            }
        }

        public void ClicaElemento(string elemento, [Optional] string descrição)
        {
            try
            {
                driver.FindElement(By.XPath(elemento)).Click(); Espera(1000); // Irá clicar em algum elemento selecionado
                if (descrição != null) Console.WriteLine("Clicou " + descrição);
            }
            catch (Exception ex)
            {
                if (descrição != null) Console.WriteLine("Não Clicou " + descrição + exceptionMsg + ex.Message); Assert.Fail();
            }

        }

        public void ValidaDados(string xpath, string valor, [Optional] string descrição)  // Irá verificar se contém o texto que for passado como valor
        {
            try
            {
                Assert.That(driver.FindElement(By.XPath(xpath)).Text.Contains(valor), Is.True);
                if (descrição != null) Console.WriteLine("Validou " + descrição);
            }
            catch (Exception ex)
            {
                if (descrição != null) Console.WriteLine("Não validou " + descrição + exceptionMsg + ex.Message); Assert.Fail();
            }
        }

        public void MenuDropDown(string xPath, string value, [Optional] string descrição)
        {
            try
            {
                string xPathValue = "//*[text()='" + value + "']";
                driver.FindElement(By.XPath(xPath)).Click(); EsperaElemento(xPathValue);
                driver.FindElement(By.XPath(xPathValue)).Click();
                if (descrição != null) Console.WriteLine("Selecinou menu Dropdown " + descrição);
            }
            catch (Exception ex)
            {
                if (descrição != null) Console.WriteLine("Erro ao selecionar o menu dropdown " + descrição + exceptionMsg + ex.Message); Assert.Fail();
            }
        }
        #endregion



        #region Funções de Manipulação
        public static void Espera(int tempo) => Thread.Sleep(tempo); // Utilizado para esperar algo (o valor deve ser inserido em milissegundo)

        public void LimparCampo(string elemento) => driver.FindElement(By.XPath(elemento)).Clear(); // Vai limpar um determinado campo

        public void ClicarFora() => driver.FindElement(By.XPath("//html")).Click(); // Irá clicar em algum campo a fora

        public void EsperaElemento(string elemento, int segundos = 90)  // Essa função vai espera que um elemento surja na tela até prosseguir algum loading
        {
            var wait = new WebDriverWait(driver, TimeSpan.FromSeconds(segundos));
            wait.Until((d) => { return d.FindElement(By.XPath(elemento)); });
        }

        public void EsperaElementoSumir(string elemento) // Essa função vai aguarda e dar sequencia no processo só quando o elemento atribuido sumir
        {
            var wait = new WebDriverWait(driver, TimeSpan.FromSeconds(90));
            wait.Until(d => d.FindElements(By.XPath(elemento)).Count == 0);
        }

        #endregion



        #region Funções de atribuições

        public static string GeraNomesAleatorios()
        {
            var rnd = new Random();
            string[] nome = File.ReadAllLines(@"C:\Users\JN\Documents\MEUS PROJETOS\Projetos\Pasta-de-estudo\Testes-automatizados\Selenium\Testando_Site_Correios\Dados\nomes.txt");
            string[] sobrenome = File.ReadAllLines(@"C:\Users\JN\Documents\MEUS PROJETOS\Projetos\Pasta-de-estudo\Testes-automatizados\Selenium\Testando_Site_Correios\Dados\sobrenomes.txt");
            string nomeCompleto = nome[rnd.Next(nome.Length)] + " " + sobrenome[rnd.Next(sobrenome.Length)];
            Console.WriteLine();

            return nomeCompleto;
        }

        public static string GeraUsuarioAleatorios()
        {
            var rnd = new Random();
            string[] nome = File.ReadAllLines(@"C:\Users\JN\Documents\MEUS PROJETOS\Projetos\Pasta-de-estudo\Testes-automatizados\Selenium\Testando_Site_Correios\Dados\nomes.txt");
            string[] sobrenome = File.ReadAllLines(@"C:\Users\JN\Documents\MEUS PROJETOS\Projetos\Pasta-de-estudo\Testes-automatizados\Selenium\Testando_Site_Correios\Dados\sobrenomes.txt");
            string nomeCompleto = nome[rnd.Next(nome.Length)] + sobrenome[rnd.Next(sobrenome.Length)];
            Console.WriteLine();

            return nomeCompleto.ToLower();
        }

        public static string GeraEmailAleatorios()
        {
            var rnd = new Random();
            string[] nome = File.ReadAllLines(@"C:\Users\JN\Documents\MEUS PROJETOS\Projetos\Pasta-de-estudo\Testes-automatizados\Selenium\Testando_Site_Correios\Dados\nomes.txt");
            string[] sobrenome = File.ReadAllLines(@"C:\Users\JN\Documents\MEUS PROJETOS\Projetos\Pasta-de-estudo\Testes-automatizados\Selenium\Testando_Site_Correios\Dados\sobrenomes.txt");
            string[] dominio = File.ReadAllLines(@"C:\Users\JN\Documents\MEUS PROJETOS\Projetos\Pasta-de-estudo\Testes-automatizados\Selenium\Testando_Site_Correios\Dados\dominio.txt");
            string emailCompleto = nome[rnd.Next(nome.Length)] + "." + sobrenome[rnd.Next(sobrenome.Length)] + dominio[rnd.Next(dominio.Length)];
            
            return emailCompleto.ToLower();
        }

        public static string GeraDataNascimento()
        {
            var rnd = new Random();
            int dia = rnd.Next(1, 28);
            int mes = rnd.Next(1, 12);
            int ano = rnd.Next(1950, 2003);
            string dataNasc = dia.ToString().PadLeft(2, '0') + mes.ToString().PadLeft(2, '0') + ano;
            return dataNasc;
        }

        public static string GeraCelular()
        {
            var rnd = new Random();
            string celNumber = string.Empty;
            for (int i = 0; i < 11; i++)
                celNumber = string.Concat(celNumber, rnd.Next(10));
            return celNumber;
        }

        public static string GeraSenha()
        {
            string senhaPadrao = "minhasenha321";
            return senhaPadrao;
        }
        #endregion
    }
}
