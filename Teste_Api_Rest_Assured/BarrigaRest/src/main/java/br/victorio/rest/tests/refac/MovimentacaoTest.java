package br.victorio.rest.tests.refac;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import br.victorio.rest.tests.Movimentacao;
import br.victorio.utils.DataUtils;
import br.victorio.utils.Utils;

public class MovimentacaoTest {
	private Movimentacao getMovimentacaoValida() {
		Movimentacao mov = new Movimentacao();
		mov.setConta_id(Utils.getIdContaPeloNome("Conta para movimentacoes"));
		mov.setDescricao("Descricao da movimentacao");
		mov.setEnvolvido("Envolvido na movimentacao");
		mov.setTipo("REC");
		mov.setData_transacao(DataUtils.getDataDiferencaDias(-1));
		mov.setData_pagamento(DataUtils.getDataDiferencaDias(5));
		mov.setValor(100f);
		mov.setStatus(true);
		return mov;
	}
	
	@Test
	public void deveInserirMovimentacaoSucesso() {
		Movimentacao mov = getMovimentacaoValida();
		
		given()
	        .body(mov)
	    .when()
	        .post("/transacoes")
	    .then()
	        .statusCode(201);
	}
	
	@Test
	public void deveValidarCamposObrigatoriosNaMovimentacao() {
	    given()
	        .body("{}")
	    .when()
	        .post("/transacoes")
	    .then()
	        .statusCode(400)
	    	.body("$", hasSize(8))
	    	.body("msg", hasItems(
	    			"Data da Movimentação é obrigatório",
	    			"Data do pagamento é obrigatório",
	    			"Descrição é obrigatório",
	    			"Interessado é obrigatório",
	    			"Valor é obrigatório",
	    			"Valor deve ser um número",
	    			"Conta é obrigatório",
	    			"Situação é obrigatório"
	    			));
	}
	
	@Test
	public void naoDeveCadastrarMovimentacaoFutura() {
		Movimentacao mov = getMovimentacaoValida();
		mov.setData_transacao(DataUtils.getDataDiferencaDias(2));
		
	    given()
	        .body(mov)
	    .when()
	        .post("/transacoes")
	    .then()
	        .statusCode(400)
	        .body("$", hasSize(1))
	        .body("msg", hasItem("Data da Movimentação deve ser menor ou igual à data atual"));
	}
	
	@Test
	public void naoDeveRemoverContaComMovimentação() {
	    given()
        .pathParam("id", Utils.getIdContaPeloNome("Conta com movimentacao"))
    .when()
        .delete("/contas/{id}")
    .then()
        .statusCode(500)
    	.body("constraint", is("transacoes_conta_id_foreign"));
	}
	
	@Test
	public void deveRemoverMovimentacao() {
		Integer MOV_ID = Utils.getIdMovimentacaoPelaDescricao("Movimentacao para exclusao");
	    given()
	        .pathParam("id", MOV_ID)
	    .when()
	        .delete("/transacoes/{id}")
	    .then()
	        .statusCode(204);
	}
	
}