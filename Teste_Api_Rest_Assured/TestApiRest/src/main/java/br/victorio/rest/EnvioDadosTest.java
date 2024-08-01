package br.victorio.rest;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.http.ContentType;

public class EnvioDadosTest {
	
    @Test
    // Envia valor via query
    public void deveEnviarValorViaQuery() {
        given()
            .log().all() // Habilita o log de todas as requisições e respostas
        .when()
            .get("http://restapi.wcaquino.me/v2/users?format=json") // Faz uma requisição GET para a URL especificada com o formato JSON
        .then()
            .log().all() // Habilita o log de todas as validações
            .statusCode(200) // Valida se o status code da resposta é 200
            .contentType(ContentType.JSON); // Valida se o tipo de conteúdo da resposta é JSON
    }
    
    @Test
    // Envia valor via query utilizando parâmetros
    public void deveEnviarValorViaQueryViaParam() {
        given()
            .log().all() // Habilita o log de todas as requisições e respostas
            .queryParam("format", "xml") // Adiciona um parâmetro "format" com o valor "xml"
            .queryParam("outra", "coisa") // Adiciona um parâmetro "outra" com o valor "coisa"
        .when()
            .get("http://restapi.wcaquino.me/v2/users") // Faz uma requisição GET para a URL especificada com os parâmetros adicionados
        .then()
            .log().all() // Habilita o log de todas as validações
            .statusCode(200) // Valida se o status code da resposta é 200
            .contentType(ContentType.XML) // Valida se o tipo de conteúdo da resposta é XML
            .contentType(Matchers.containsString("utf-8")); // Valida se o tipo de conteúdo da resposta contém "utf-8"
    }
    
    @Test
    // Envia valor via query utilizando header
    public void deveEnviarValorViaQueryViaHeader() {
        given()
            .accept(ContentType.XML) // Define o tipo de conteúdo aceito como XML no header da requisição
        .when()
            .get("http://restapi.wcaquino.me/v2/users") // Faz uma requisição GET para a URL especificada
        .then()
            .log().all() // Habilita o log de todas as validações
            .statusCode(200) // Valida se o status code da resposta é 200
            .contentType(ContentType.XML); // Valida se o tipo de conteúdo da resposta é XML
    }
	
}
