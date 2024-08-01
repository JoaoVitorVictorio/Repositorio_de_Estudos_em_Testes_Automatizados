package br.victorio.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class UserJsonTest {

	@Test
	public void validarPrimeiroNivel() {
		// Envia uma requisição GET para http://restapi.wcaquino.me/users/1 e valida o primeiro nível do JSON retornado
		given()
		.when()
			.get("http://restapi.wcaquino.me/users/1")
		.then()
			.statusCode(200) // Verifica se o código de status é 200 (OK)
			.body("id", is(1)) // Verifica se o campo "id" é igual a 1
			.body("name", containsString("João")) // Verifica se o campo "name" contém "João"
			.body("age", greaterThan(18)); // Verifica se o campo "age" é maior que 18
	}
	
	@Test
	public void deveVerificarPrimeiroNivelOutrasFormas() {
		// Envia uma requisição GET para http://restapi.wcaquino.me/users/1
		Response response = RestAssured.request(Method.GET, "http://restapi.wcaquino.me/users/1");
		
		// Path
		// Utilizando o método path para obter o valor do campo "id" e comparar com o esperado
		Assert.assertEquals(new Integer(1), response.path("id"));
		// Outra forma de utilizar o método path para obter o valor do campo "id" e comparar com o esperado
		Assert.assertEquals(new Integer(1), response.path("%s", "id"));
		
		// JsonPath
		// Criando um objeto JsonPath com a resposta e comparando o valor do campo "id"
		JsonPath jpath = new JsonPath(response.asString());
		Assert.assertEquals(1, jpath.getInt("id"));
		
		// From
		// Utilizando o método from da classe JsonPath para obter o valor do campo "id" e comparar com o esperado
		int id = JsonPath.from(response.asString()).getInt("id");
		Assert.assertEquals(1, id);
	}
	
	@Test
	public void deveVerificarSegundoNivel() {
		// Envia uma requisição GET para http://restapi.wcaquino.me/users/1 e valida o primeiro nível do JSON retornado
		given()
		.when()
			.get("http://restapi.wcaquino.me/users/2")
		.then()
			.statusCode(200) // Verifica se o código de status é 200 (OK)
			.body("name", containsString("Joaquina")) // Verifica se o campo "name" contém "Joaquina"
			.body("endereco.rua", is("Rua dos bobos")); // Verifica valor encontrado em segundo nivel
	}
	
	@Test
	public void deveVerificarLista() {
		// Envia uma requisição GET para http://restapi.wcaquino.me/users/3 e valida o primeiro nível do JSON retornado
		given()
		.when()
			.get("http://restapi.wcaquino.me/users/3")
		.then()
			.statusCode(200) // Verifica se o código de status é 200 (OK)
			.body("name", containsString("Júlia")) // Verifica se o campo "name" contém "Júlia"
			.body("filhos", hasSize(2)) // Verifica se o campo "filhos" possui tamanho 2
			.body("filhos[0].name", is("Zezinho")) // Verifica se o primeiro filho tem o nome "Zezinho"
			.body("filhos[1].name", is("Luizinho")) // Verifica se o segundo filho tem o nome "Luizinho"
			.body("filhos.name", hasItem("Zezinho")) // Verifica se há pelo menos um filho com o nome "Zezinho"
			.body("filhos.name", hasItems("Zezinho", "Luizinho")); // Verifica se há filhos com os nomes "Zezinho" e "Luizinho"
	}
	
	@Test
	public void deveValidarMensagemDeErro() {
		given()
		.when()
			.get("http://restapi.wcaquino.me/users/4")
		.then()
			.statusCode(404) // Verifica se o código de status é 404 (Erro, de recurso não existente)
			.body("error", is("Usuário inexistente")); // Valida a mensagem de erro exibida o returno
	}
	
	@Test
	public void deveVerificarListaNaRaiz() {
		// Envia uma requisição GET para http://restapi.wcaquino.me/users e valida a lista na raiz do JSON retornado
		given()
		.when()
			.get("http://restapi.wcaquino.me/users")
		.then()
			.statusCode(200) // Verifica se o código de status é 200 (OK)
			.body("$", hasSize(3)) // Verifica se a lista na raiz possui tamanho 3
			.body("name", hasItems("João da Silva", "Maria Joaquina", "Ana Júlia")) // Verifica se a lista na raiz contém os nomes especificados
			.body("age[1]", is(25)) // Verifica se a idade do segundo usuário é 25
			.body("filhos.name", hasItem(Arrays.asList("Zezinho", "Luizinho"))) // Verifica se há pelo menos um filho com os nomes "Zezinho" e "Luizinho"
			.body("salary", contains(1234.5678f, 2500, null)); // Verifica se os salários contêm os valores especificados
	}
	
	@Test
	public void deveFazerVerificacoesAvancadas() {
		// Envia uma requisição GET para http://restapi.wcaquino.me/users e valida a lista na raiz do JSON retornado
		given()
		.when()
			.get("http://restapi.wcaquino.me/users")
		.then()
			.statusCode(200) // Verifica se o código de status é 200 (OK)
			.body("$", hasSize(3)) // Verifica se a lista na raiz possui tamanho 3
			.body("age.findAll{it <= 25}.size()", is(2)) // Verifica o número de usuários com idade até 25 anos
			.body("age.findAll{it <= 25 && it > 20}.size()", is(1)) // Verifica o número de usuários com idade entre 20 e 25 anos
			.body("findAll{it.age <= 25 && it.age > 20}.name", hasItem("Maria Joaquina")) // Verifica se há um usuário com idade entre 20 e 25 anos e nome "Maria Joaquina"
			.body("findAll{it.age <= 25}[0].name", is("Maria Joaquina")) // Verifica o nome do primeiro usuário com idade até 25 anos
			.body("findAll{it.age <= 25}[1].name", is("Ana Júlia")) // Verifica o nome do segundo usuário com idade até 25 anos
			.body("findAll{it.name.contains('n')}.name", hasItems("Maria Joaquina", "Ana Júlia")) // Verifica se há usuários com nomes que contêm 'n'
			.body("findAll{it.name.length() > 10}.name", hasItems("Maria Joaquina", "João da Silva")) // Verifica se há usuários com nomes com mais de 10 caracteres
			.body("name.collect{it.toUpperCase()}", hasItems("MARIA JOAQUINA")) // Verifica se os nomes foram convertidos para maiúsculas corretamente
			.body("name.findAll{it.startsWith('Maria')}collect{it.toUpperCase()}", hasItems("MARIA JOAQUINA")) // Verifica se os nomes que começam com "Maria" foram convertidos para maiúsculas corretamente
			.body("age.collect{it * 2}", hasItems(60, 50 ,40)) // Verifica se a multiplicação por 2 da idade resulta nos valores esperados
			.body("id.max()", is(3)) // Verifica se o maior ID é 3
			.body("salary.min()", is(1234.5678f)) // Verifica se o menor salário é 1234.5678
			.body("salary.findAll{it != null}.sum()", is(closeTo(3734.5678f, 0.001))); // Verifica se a soma dos salários é próxima de 3734.5678
	}

	
	@Test
	public void devoUnirJsComJava() {
		ArrayList<String> names = 
			given()
				.when()
					.get("http://restapi.wcaquino.me/users")
				.then()
					.statusCode(200)
					.extract().path("name.findAll{it.startsWith('Maria')}");
		Assert.assertEquals(1, names.size());
		Assert.assertTrue(names.get(0).equals("Maria Joaquina"));
		Assert.assertEquals(names.get(0).toUpperCase(), "Maria Joaquina".toUpperCase());
		}
}
