package br.victorio.rest.tests.refac;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.victorio.core.BaseTest;
import io.restassured.RestAssured;

@RunWith(Suite.class)
@SuiteClasses({
	ContasTest.class,
	MovimentacaoTest.class,
	SaldoTest.class,
	AuthTest.class
})

public class SuiteGeral extends BaseTest{
	@BeforeClass
	public static void login() {
	    Map<String, String> login = new HashMap<>();
	    login.put("email", "joaovictorioteste@gmail.com");
	    login.put("senha", "123456");
	    String TOKEN = given()
	        .body(login)
	    .when()
	        .post("/signin")
	    .then()
	        .statusCode(200)
	        .extract().path("token");
	    
	   RestAssured.requestSpecification.header("Authorization", "JWT " + TOKEN);
	   
	   RestAssured.get("/reset").then().statusCode(200);
	}
}
