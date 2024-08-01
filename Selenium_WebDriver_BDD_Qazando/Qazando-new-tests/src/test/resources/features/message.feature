# language: pt

@mensagem
Funcionalidade: Enviar mensagem

  @enviar-mensagem-sucesso
  Cenario: Enviar uma mensagem com sucesso
    Dado que estou na tela de mensagens
    E preencho todos os campos
    Quando eu clico em enviar mensagem
    Então vejo feedback de mensagem enviada com sucesso
