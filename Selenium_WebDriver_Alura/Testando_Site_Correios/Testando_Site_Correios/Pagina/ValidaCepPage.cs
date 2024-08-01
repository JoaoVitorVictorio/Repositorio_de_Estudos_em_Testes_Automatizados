using Microsoft.VisualBasic;
using NUnit.Framework;
using OpenQA.Selenium;
using System.IO;
using Testando_Site_Correios.Essencial;

namespace Testando_Site_Correios.Pagina
{
    public class ValidaCepPage : Começo // ValidaCepPage irá puxar as funçoes do DSL onde vou atribuir os valores que eu preciso para meu teste.
    {
        public void PreencheCep() // Irá prencher um campo conforme o elemento XPath e o valor que foi atribuido
        {
            PreencheCampos("//*[@id=\'endereco\']", "86056670", "o campo CEP com o valor 86056670");
        }

        public void ClicaBtnBuscar() // Irá clicar no elemento XPath que foi atribuido.
        {
            ClicaElemento("//*[@id=\'btn_pesquisar\']", "no botão pesquisar");
        }

        public void ValidaResultado() // Irá validar se possui um valor que foi atribuido no XPath
        {
            ValidaDados("//*[@id=\'resultado-DNEC\']/tbody/tr/td[1]", "Rua Olympio Theodoro", "o endereço Rua Olympio Theodoro");
        }

        public void ValidaResultadoTotal() // Irá validar se possui um valor que foi atribuido no XPath
        {
            string[] dados = // "Faz um loop para varrer todos os dados do resultado no buscador de CEP"
            {
                "Rua Olympio Theodoro",
                "Colinas",
                "Londrina/PR",
                "86056-670"
            };

            for (int i = 0; i < dados.Length; i++) // Estrutura de repetição que percorre cada elemento no array 'dados', começando com índice 0 e incrementando em 1 até atingir o tamanho do array.
            {
                ValidaDados("//*[@id=\'resultado-DNEC\']/tbody/tr/td[" + (i + 1) + "]", dados[i]);
                System.Console.WriteLine(dados[i]);
            } 
        }

        public void ValidaMultiplosCeps() 
        {
            string[] ceps = File.ReadAllLines(@"C:\Users\JN\Documents\MEUS PROJETOS\Projetos\Pasta-de-estudo\Testes-automatizados\Selenium\Testando_Site_Correios\Dados\ceps.txt"); // Lê as linhas de um arquivo de texto e armazena-os em um array de strings, simulando a leitura de dados de um banco de dados para realizar testes com múltiplos ceps.
            //{
            //"86056670",
            //"86187010",
            //"01409020",
            //"01409030"
            //};

            string[] logradouros = File.ReadAllLines(@"C:\Users\JN\Documents\MEUS PROJETOS\Projetos\Pasta-de-estudo\Testes-automatizados\Selenium\Testando_Site_Correios\Dados\logradouros.txt"); // Lê as linhas de um arquivo de texto e armazena-os em um array de strings, simulando a leitura de dados de um banco de dados para realizar testes com múltiplos logradouros.
            //{
            //"Rua Olympio Theodoro",
            //"Rua dos Bandeirantes",
            //"Rua Lupércio de Camargo",
            //"Rua Professor Azevedo Amaral"
            //};

            for (int i = 0; i < ceps.Length; i++)
            {
                PreencheCampos("//*[@id=\'endereco\']", ceps[i]);
                ClicaElemento("//*[@id=\'btn_pesquisar\']");
                ValidaDados("//*[@id=\'resultado-DNEC\']/tbody/tr/td[1]", logradouros[i]);
                ClicaElemento("//*[@id=\"btn_nbusca\"]", "no botão pesquisar e validou o CEP: " + ceps[i] + " para a rua: " + logradouros[i]);
            }
        }
        public void SelecionaTipoCEP() // Valida interação com menu de seleção, selecionando o valor que foi passado
        {
            string tipoCep = "Localidade/Logradouro";
            MenuDropDown("//select[@id='tipoCEP']", tipoCep, "Tipo do CEP: " + tipoCep);
        }

        public void ValidaItensMenuDropDown() // Verifica todos os itens que estão dentro do array e valida se os mesmos itens possui no Menu do site.
        {
            string[] itensMenu =
            {
                "Localidade/Logradouro",
                "CEP Promocional",
                "Caixa Postal Comunitária",
                "Grande Usuário",
                "Unidade Operacional",
                "Todos"
            };

            for (int i = 0; i < itensMenu.Length; i++)
            {
                MenuDropDown("//select[@id='tipoCEP']", itensMenu[i], "Tipo do CEP: " + itensMenu[i]);
            }
                
        }
    }
}
