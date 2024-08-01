# language: pt

  Funcionalidade: Receber o cupom de desconto da qazando
    eu como usuário da qazando
    quero receber um cupom de desconto
    para comprar um curso com valor reduzido

    @gerar-cupom
  Cenario: Visualizar código de desconto
    Dado que estou no site da qazando
    Quando eu preencho meu e-mail
    E clico no em ganhar cupom
    Entao eu vejo o código de desconto
