package br.victorio.rest;

import org.junit.Test;

import io.restassured.RestAssured;

public class AtributosEstaticos {
	
    @Test 
    public void utilizandoAtributosEstaticos() {
       RestAssured.baseURI = "http://restapi.wcaquino.me"; // Definindo o URI base para as requisições
       RestAssured.port = 80; // Definindo a porta para as requisições
       RestAssured.basePath = "/v2"; // Definindo o caminho base para as requisições
        
        RestAssured.given() 
                .log().all()	
            .when() 
                .get("/users") // Realizando uma requisição GET para "/users" e validando o código de status
            .then() 
                .statusCode(200);
    }

}
