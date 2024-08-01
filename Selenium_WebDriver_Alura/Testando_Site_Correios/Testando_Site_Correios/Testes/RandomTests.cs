using NUnit.Framework;
using System.Security.Cryptography;
using Testando_Site_Correios.Essenciais;
using Testando_Site_Correios.Essencial;

namespace Testando_Site_Correios.Testes
{
    internal class RandomTests : DSL
    {
        [Test]
        public void RandomData() // Gera nomes aleatórios de acordo com os dados que estão em um arquivo de texto e exibe no console.
        {
            string nome = GeraNomesAleatorios();
            System.Console.WriteLine(nome);
        }

        [Test]
        public void RandomEmail() // Gera emails aleatórios de acordo com os dados que estão em um arquivo de texto e exibe no console.
        {
            string email = GeraEmailAleatorios();
            System.Console.WriteLine(email);
        }

        [Test]
        public void RandomNascimento() // Gera emails aleatórios de acordo com os dados que estão em um arquivo de texto e exibe no console.
        {
            string dataNasc = GeraDataNascimento();
            System.Console.WriteLine(dataNasc);
        }

        [Test]
        public void RandomCelular() // Gera número de celulares aleatórios e e exibe no console.
        {
            string cel = GeraCelular();
            System.Console.WriteLine(cel);
        }
    }
}
