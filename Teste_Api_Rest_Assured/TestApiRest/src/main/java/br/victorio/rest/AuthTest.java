package br.victorio.rest;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.http.ContentType;

public class AuthTest {
	
	@Test
	public void deveAcessarSWAPI() {
		given()
			.log().all()
		.when()
			.get("https://swapi.dev/api/people/1")
		.then()
			.log().all()
			.statusCode(200)
			.body("name", Matchers.is("Luke Skywalker"));
		}


	@Test
	public void deveObterClima() {
		given()
			.log().all()
			.queryParam("q", "Londrina,BR")
			.queryParam("appid", "a79ab193365f4ba949153daba6c45d2f")
			.queryParam("units", "metric")
		.when()
			.get("https://api.openweathermap.org/data/2.5/weather")
		.then()	
			.log().all()
			.statusCode(200)
			.body("name", Matchers.is("Londrina"))
			.body("coord.lon", Matchers.is(-51.1628f));
		}

	@Test
	public void naoDeveAcessarSemSenha() {
		given()
		.log().all()
	.when()
		.get("https://restapi.wcaquino.me/basicauth")
	.then()	
		.log().all()
		.statusCode(401);
		}
	
	@Test
	public void deveFazerAutenticacaoBasica() {
		given()
		.log().all()
	.when()
		.get("https://admin:senha@restapi.wcaquino.me/basicauth")
	.then()	
		.log().all()
		.statusCode(200)
		.body("status", Matchers.is("logado"));
		}
	
	@Test
	public void deveFazerAutenticacaoBasicaComAutenticacaoBasica() {
		given()
		.log().all()
		.auth().basic("admin", "senha")
	.when()
		.get("https://restapi.wcaquino.me/basicauth")
	.then()	
		.log().all()
		.statusCode(200)
		.body("status", Matchers.is("logado"));
		}
	
	@Test
	public void deveFazerAutenticacaoBasicaComAutenticacaoBasicaComChallenge() {
		given()
		.log().all()
		.auth().preemptive().basic("admin", "senha")
	.when()
		.get("https://restapi.wcaquino.me/basicauth2")
	.then()	
		.log().all()
		.statusCode(200)
		.body("status", Matchers.is("logado"));
		}
	
	@Test
	public void deveFazerAutenticacaoComToken() {
		Map<String, String> login = new HashMap<String, String>();
		login.put("email", "joao@gmai.com");
		login.put("senha", "123456");
		String token = given()
	        .log().all()
	        .body(login)
	        .contentType(ContentType.JSON)
        .when()
        	.post("http://barrigarest.wcaquino.me/signin")
        .then()
        	.log().all()
        	.statusCode(200)
        	.body("nome", Matchers.is("João"))
        	.extract().path("token");
		
		given()
        .log().all()
        .header("Authorization", "JWT " + token)
    .when()
    	.post("http://barrigarest.wcaquino.me/contas")
    .then()
    	.log().all()
    	.statusCode(200);
		}
	
	@Test
	public void deveAcessarAplicacaoWeb() {
		String cookie = given()
        	.log().all()
        	.formParam("email", "joao@gmai.com")
        	.formParam("senha", "123456")
        	.contentType(ContentType.URLENC.withCharset("UTF-8"))
        .when()
	        .log().all()
	        .post("http://seubarriga.wcaquino.me/logar")
        .then()
        	.log().all()
        	.statusCode(200)
        	.extract().header("set-cookie");
		
		cookie = cookie.split("=")[1].split(";")[0];
		System.out.println(cookie);
		
		 given()
	        	.log().all()
	        	.cookie("conect.sid", cookie)
	        .when()
		        .log().all()
		        .get("http://seubarriga.wcaquino.me/contas")
	        .then()
	        	.log().all()
	        	.statusCode(200)
	;}
}
