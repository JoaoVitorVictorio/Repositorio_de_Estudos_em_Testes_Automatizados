package br.victorio.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.http.ContentType;

public class VerbosRest {
	
    @Test
    public void deveSalvarUsuarioViaPost() {
        given()
            .log().all() // Log de todas as informações da requisição
            .contentType("application/json") // Tipo de conteúdo da requisição
            .body("{\"name\": \"Joaozinho\",\"age\": 26}") // Corpo da requisição com os dados do usuário
        .when()
            .post("http://restapi.wcaquino.me/users") // Envia a requisição POST para o endpoint especificado
        .then()
            .log().all() // Log de todas as informações da resposta
             .statusCode(201) // Verifica se o código de status da resposta é 201 (Created)
             .body("id", is(notNullValue())) // Verifica se o campo "id" da resposta não é nulo
             .body("name", is("Joaozinho")) // Verifica se o campo "name" da resposta é "Joaozinho"
             .body("age", is(26));	// Verifica se o campo "age" da resposta é 26
    }
    
    @Test
    public void deveSalvarUsuarioViaPostUsandoMap() {
        // Cria um mapa com os dados do usuário
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", "Usuario via map");
        params.put("age", 25);
        
        // Envia a requisição POST com os dados do mapa
        given()
            .log().all() // Log de todas as informações da requisição
            .contentType("application/json") // Tipo de conteúdo da requisição
            .body(params) // Corpo da requisição com os dados do usuário
        .when()
            .post("http://restapi.wcaquino.me/users") // Envia a requisição POST para o endpoint especificado
        .then()
            .log().all() // Log de todas as informações da resposta
             .statusCode(201) // Verifica se o código de status da resposta é 201
             .body("id", is(notNullValue())) // Verifica se o campo "id" da resposta não é nulo
             .body("name", is("Usuario via map")) // Verifica se o campo "name" da resposta é "Usuario via map"
             .body("age", is(25));	// Verifica se o campo "age" da resposta é 25
    }
    
    
    @Test
    public void deveSalvarUsuarioViaPostUsandoObjeto() {
        // Cria um objeto do tipo User com os dados do usuário
        User user = new User("Usuario via objeto", 35);
         
        // Envia a requisição POST com os dados do objeto
        given()
            .log().all() // Log de todas as informações da requisição
            .contentType("application/json") // Tipo de conteúdo da requisição
            .body(user) // Corpo da requisição com os dados do usuário
        .when()
            .post("http://restapi.wcaquino.me/users") // Envia a requisição POST para o endpoint especificado
        .then()
            .log().all() // Log de todas as informações da resposta
             .statusCode(201) // Verifica se o código de status da resposta é 201
             .body("id", is(notNullValue())) // Verifica se o campo "id" da resposta não é nulo
             .body("name", is("Usuario via objeto")) // Verifica se o campo "name" da resposta é "Usuario via objeto"
             .body("age", is(35));	// Verifica se o campo "age" da resposta é 35
    }
    
    @Test
    public void deveDeserializarObjetoAoSalvarUsuario() {
        // Cria um objeto do tipo User com os dados do usuário
        User user = new User("Usuario deserializado", 35);
         
        // Envia a requisição POST com os dados do objeto e deserializa a resposta para um objeto do tipo User
        User usuarioinserido = given()
            .log().all() // Log de todas as informações da requisição
            .contentType("application/json") // Tipo de conteúdo da requisição
            .body(user) // Corpo da requisição com os dados do usuário
        .when()
            .post("http://restapi.wcaquino.me/users") // Envia a requisição POST para o endpoint especificado
        .then()
            .log().all() // Log de todas as informações da resposta
             .statusCode(201) // Verifica se o código de status da resposta é 201
             .extract().body().as(User.class); // Deserializa a resposta para um objeto do tipo User
        
        // Imprime o objeto deserializado
        System.out.println(usuarioinserido.toString());
        
        // Verifica se os dados deserializados estão corretos
        Assert.assertEquals("Usuario deserializado", usuarioinserido.getName());
        Assert.assertThat(usuarioinserido.getAge(), is(35));
        Assert.assertThat(usuarioinserido.getId(), notNullValue());
    }
    
    @Test
    public void naoDeveSalvarUsuarioSemNome() {
        given()
            .log().all() // Log de todas as informações da requisição
            .contentType("application/json") // Tipo de conteúdo da requisição
            .body("{\"age\": 26}") // Corpo da requisição sem o campo "name"
        .when()
            .post("http://restapi.wcaquino.me/users") // Envia a requisição POST para o endpoint especificado
        .then()
            .log().all() // Log de todas as informações da resposta
             .statusCode(400) // Verifica se o código de status da resposta é 400 (Bad Request)
             .body("id", is(nullValue())) // Verifica se o campo "id" da resposta é nulo
             .body("error", is("Name é um atributo obrigatório")); // Verifica se a mensagem de erro é "Name é um atributo obrigatório"
    }
    
    @Test
    public void deveSalvarUsuarioViaXML() {
        given()
	        .log().all() // Log de todas as informações da requisição
	        .contentType(ContentType.XML) // Tipo de conteúdo da requisição
	        .body("<user><name>Joaozinho</name><age>26</age></user>") // Corpo da requisição com os dados do usuário em XML
	    .when()
        	.post("http://restapi.wcaquino.me/usersXML") // Envia a requisição POST para o endpoint especificado
        .then()
	        .log().all() // Log de todas as informações da resposta
	         .statusCode(201) // Verifica se o código de status da resposta é 201 (Created)
	         .body("id.@id", is(notNullValue())) // Verifica se o campo "id" da resposta não é nulo
	         .body("user.name", is("Joaozinho")) // Verifica se o campo "name" da resposta é "Joaozinho"
	         .body("user.age", is("26"));	// Verifica se o campo "age" da resposta é 26
    }
    
    @Test
    public void deveSalvarUsuarioViaXMLUsandoObjeto() {
        // Cria um objeto do tipo User com os dados do usuário
        User user = new User("UsuarioXML", 40);
        
        // Envia a requisição POST com os dados do objeto em formato XML
        given()
            .log().all() // Log de todas as informações da requisição
            .contentType(ContentType.XML) // Tipo de conteúdo da requisição
            .body(user) // Corpo da requisição com os dados do usuário em XML
        .when()
            .post("http://restapi.wcaquino.me/usersXML") // Envia a requisição POST para o endpoint especificado
        .then()
            .log().all() // Log de todas as informações da resposta
             .statusCode(201) // Verifica se o código de status da resposta é 201 (Created)
             .body("id.@id", is(notNullValue())) // Verifica se o campo "id" da resposta não é nulo
             .body("user.name", is("UsuarioXML")) // Verifica se o campo "name" da resposta é "UsuarioXML"
             .body("user.age", is("40"));	// Verifica se o campo "age" da resposta é 40
    }
    
    @Test
    public void deveDeserializarXMLAoSalvarUsuario() {
        // Cria um objeto do tipo User com os dados do usuário
        User user = new User("Usuario deserializado", 40);
        
        // Envia a requisição POST com os dados do objeto em formato XML e deserializa a resposta para um objeto do tipo User
        User usuarioInserido = given()
            .log().all() // Log de todas as informações da requisição
            .contentType(ContentType.XML) // Tipo de conteúdo da requisição
            .body(user) // Corpo da requisição com os dados do usuário em XML
        .when()
            .post("http://restapi.wcaquino.me/usersXML") // Envia a requisição POST para o endpoint especificado
        .then()
            .log().all() // Log de todas as informações da resposta
             .statusCode(201) // Verifica se o código de status da resposta é 201
             .extract().body().as(User.class); // Deserializa a resposta para um objeto do tipo User
        
        // Imprime o objeto deserializado
        System.out.println(usuarioInserido.toString());
        
        // Verifica se os dados deserializados estão corretos
        Assert.assertEquals("Usuario deserializado", usuarioInserido.getName());
        Assert.assertThat(usuarioInserido.getAge(), is(40));
        Assert.assertThat(usuarioInserido.getId(), notNullValue());
    }
    
    @Test
    public void deveAlterarUsuarioViaPut() {
        given()
            .log().all() // Log de todas as informações da requisição
            .contentType("application/json") // Tipo de conteúdo da requisição
            .body("{\"name\": \"Usuario Alterado\",\"age\": 80}") // Corpo da requisição com os dados do usuário
        .when()
            .put("http://restapi.wcaquino.me/users/1") // Envia a requisição PUT para o endpoint especificado
        .then()
            .log().all() // Log de todas as informações da resposta
             .statusCode(200) // Verifica se o código de status da resposta é 200
             .body("id", is(1)) // Verifica se o campo "id" da resposta não é nulo
             .body("name", is("Usuario Alterado")) // Verifica se o campo "name" da resposta é "Joaozinho"
             .body("age", is(80))	// Verifica se o campo "age" da resposta é 26
             .body("salary", is(1234.5678f)); //Verifica se o campo "salary" é 1234.5678
    }
	
    @Test
    public void devoCustomizarURL() {
        given()
            .log().all() // Log de todas as informações da requisição
            .contentType("application/json") // Tipo de conteúdo da requisição
            .body("{\"name\": \"Usuario Alterado\",\"age\": 80}") // Corpo da requisição com os dados do usuário
        .when()
            .put("http://restapi.wcaquino.me/{entidade}/{userId}", "users", "1") // Envia a requisição PUT com url customizada
        .then()
            .log().all() // Log de todas as informações da resposta
             .statusCode(200) // Verifica se o código de status da resposta é 200
             .body("id", is(1)) // Verifica se o campo "id" da resposta não é nulo
             .body("name", is("Usuario Alterado")) // Verifica se o campo "name" da resposta é "Joaozinho"
             .body("age", is(80))	// Verifica se o campo "age" da resposta é 26
             .body("salary", is(1234.5678f)); //Verifica se o campo "salary" é 1234.5678
    }
    
    @Test
    public void devoCustomizarURLParte2() {
        given()
            .log().all() // Log de todas as informações da requisição
            .contentType("application/json") // Tipo de conteúdo da requisição
            .body("{\"name\": \"Usuario Alterado\",\"age\": 80}") // Corpo da requisição com os dados do usuário
            .pathParam("entidade", "users")
            .pathParam("userId", "1")
        .when()
            .put("http://restapi.wcaquino.me/{entidade}/{userId}") // Envia a requisição PUT com url customizada
        .then()
            .log().all() // Log de todas as informações da resposta
             .statusCode(200) // Verifica se o código de status da resposta é 200
             .body("id", is(1)) // Verifica se o campo "id" da resposta não é nulo
             .body("name", is("Usuario Alterado")) // Verifica se o campo "name" da resposta é "Joaozinho"
             .body("age", is(80))	// Verifica se o campo "age" da resposta é 26
             .body("salary", is(1234.5678f)); //Verifica se o campo "salary" é 1234.5678
    }
    
    @Test
    public void deverRemoverUsuario() {
        given()
            .log().all() // Log de todas as informações da requisição
        .when()
            .delete("http://restapi.wcaquino.me/users/1") // Envia a requisição DELETE para o endpoint especificado
        .then()
            .log().all() // Log de todas as informações da resposta
             .statusCode(204); // Verifica se o código de status da resposta é 204 (No Content)
    }
    
    @Test
    public void naoDeverRemoverUsuarioInexistente() {
        given()
            .log().all() // Log de todas as informações da requisição
        .when()
            .delete("http://restapi.wcaquino.me/users/5") // Envia a requisição DELETE para um usuário inexistente
        .then()
            .log().all() // Log de todas as informações da resposta
             .statusCode(400) // Verifica se o código de status da resposta é 400 (Bad Request)
             .body("error", is("Registro inexistente")); // Verifica se a mensagem de erro é "Registro inexistente"
    }
}