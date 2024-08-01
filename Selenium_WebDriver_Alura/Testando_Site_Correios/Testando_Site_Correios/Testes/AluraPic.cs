using NUnit.Framework;
using Testando_Site_Correios.Essencial;

namespace Testando_Site_Correios.Testes
{
    internal class CadastroAluraPic : AluraPic
    {
        [Test]
        public void PreencheCadastroAluraPic() // Teste para Cadastro de usuario no AluraPic utilizando
        {

            ClicaElemento("/html/body/app-root/ng-component/div/div/div[2]/ng-component/p/a", "em Register now");

            string email = GeraEmailAleatorios();
            PreencheCampos("/html/body/app-root/ng-component/div/div/div[2]/ng-component/form/div[1]/input", email, "o email");

            string nome = GeraNomesAleatorios();
            PreencheCampos("/html/body/app-root/ng-component/div/div/div[2]/ng-component/form/div[2]/input", nome, "o nome completo");

            string usuario = GeraUsuarioAleatorios();
            PreencheCampos("/html/body/app-root/ng-component/div/div/div[2]/ng-component/form/div[3]/input", usuario, "o user name");

            string senha = GeraSenha();
            PreencheCampos("/html/body/app-root/ng-component/div/div/div[2]/ng-component/form/div[4]/input", senha, "a senha");

            ClicaElemento("/html/body/app-root/ng-component/div/div/div[2]/ng-component/form/button", "em Registrar");
        }
    }
}
