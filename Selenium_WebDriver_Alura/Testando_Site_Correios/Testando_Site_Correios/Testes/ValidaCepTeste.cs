using NUnit.Framework;
using System.IO;
using Testando_Site_Correios.Pagina;

namespace Testando_Site_Correios.Testes
{
    public class ValidaCepTeste : ValidaCepPage // ValidaCepTeste irá executar os testes de acordo com os valores que foram atribuidos no ValidaCepPage
    {
        [Test]
        public void ValidaCep() 
        {
            PreencheCep();
            ClicaBtnBuscar();
            ValidaResultado();
        }

        [Test]
        public void ValidarResultados() 
        {
            PreencheCep();
            ClicaBtnBuscar();
            ValidaResultadoTotal();
        }

        [Test]
        public void ValidarResultadosMultiplos()
        {
            ValidaMultiplosCeps();
        }

        [Test]
        public void SelecionaMenuTipoCep()
        {
            SelecionaTipoCEP();
            ValidaCep();
        }

        [Test]
        public void ValidaTodosItensMenu()
        {
            ValidaItensMenuDropDown();
        }
    }
}
