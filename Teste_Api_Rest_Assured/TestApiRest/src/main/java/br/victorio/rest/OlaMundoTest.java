package br.victorio.rest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class OlaMundoTest {
	@Test
	public void testOlaMundo() {
		// Envia uma requisição GET para http://restapi.wcaquino.me/ola
		Response response = RestAssured.request(Method.GET, "http://restapi.wcaquino.me/ola");
		// Verifica se a resposta possui o corpo "Ola Mundo!"
		Assert.assertEquals("Ola Mundo!", response.getBody().asString());
		// Verifica se o código de status da resposta é 200 (OK)
		Assert.assertEquals(200, response.statusCode());

		// Realiza a validação utilizando a sintaxe fluente
		ValidatableResponse validacao = response.then();
		// Verifica se o código de status da resposta é 200 (OK)
		validacao.statusCode(200);
	}

	@Test
	public void conhecendoOutrasFormasRestAssured() {
		// Envia uma requisição GET para http://restapi.wcaquino.me/ola e verifica se o código de status é 200
		get("http://restapi.wcaquino.me/ola").then().statusCode(200);
	}

	@Test
	public void conhecendoOutrasFormasRestAssuredComModoFluente() {
		// Envia uma requisição GET para http://restapi.wcaquino.me/ola e verifica se o código de status é 200
		given().when().get("http://restapi.wcaquino.me/ola").then().statusCode(200);
	}
	
	@Test
	public void conhecendoMatchersHamcrest() {
		// Verifica se o valor é "Maria"
		Assert.assertThat("Maria", Matchers.is("Maria"));
		// Verifica se o valor é 128
		Assert.assertThat(128, Matchers.is(128));
		// Verifica se a lista de inteiros possui tamanho 5
		List<Integer> impares = Arrays.asList(1, 2, 3, 4, 5);
		Assert.assertThat(impares, Matchers.hasSize(5));
	}
	
	@Test
	public void devoValidarBody() {
		given() // Dado
		.when() // Quando
		.get("http://restapi.wcaquino.me/ola") // Pegar
		.then() // Então
		.statusCode(200) // Valida status cod de retorno
		.body(is("Ola Mundo!")) // Valida valor encontrado no Body
		.body(containsString("Mundo")); // Valida se contém só uma parte da String encontrada no Body
	}
}
