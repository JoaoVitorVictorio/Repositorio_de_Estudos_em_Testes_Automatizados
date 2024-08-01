# language: pt

  @cadastro
  Funcionalidade: Cadastro de usuário

    @cadastro_sucesso
    Cenario: Registrar novo usuário com sucesso
      Dado que estou na tela de login
      E acesso o para cadastrar usuário
      Quando eu preencho o formulario de cadastro
      E clico em registar
      Então vejo a mensagem de cadastro realizado com sucesso
