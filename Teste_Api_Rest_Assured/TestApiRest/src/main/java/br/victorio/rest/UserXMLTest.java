package br.victorio.rest;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasXPath;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.internal.path.xml.NodeImpl;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class UserXMLTest {
	
    public static RequestSpecification reqSpec;
    public static ResponseSpecification resSpec;
    
    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://restapi.wcaquino.me"; // Definindo o URI base para as requisições
        
        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        
        reqBuilder.log(LogDetail.ALL); // Adiciona log detalhado para a requisição
        reqSpec = reqBuilder.build();
        
        ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
        resBuilder.expectStatusCode(200); // Espera que o status code seja 200
        resSpec = resBuilder.build();
        
        
        // Define as especificações de requisição e resposta para o RestAssured
		RestAssured.requestSpecification = reqSpec;
		RestAssured.responseSpecification = resSpec;
        
    }	
    
    @Test 
    public void devoTrabalharComXml() {
        RestAssured.given() // Inicia a configuração do teste RestAssured
            .when() // Define a seção 'quando' da solicitação
                .get("/usersXML/3") // Define o tipo de solicitação (GET) e a URL
            .then() // Inicia a verificação da resposta
                .rootPath("user") // Define o caminho raiz para 'user' simplificando as verificações subsequentes
                .body("name", is("Ana Julia")) // Verifica se o nome do usuário é "Ana Julia"
                .body("@id", is("3")) // Verifica se o atributo id do usuário é "3"
                .body("age", is("20")) // Verifica se a idade do usuário é "20"
                .body("filhos.name", hasItems("Zezinho", "Luizinho")) // Verifica se os nomes dos filhos contêm "Zezinho" e "Luizinho"
                .body("filhos.name[0]", is("Zezinho")) // Verifica se o primeiro nome na lista de filhos é "Zezinho"
                .body("filhos.name[1]", is("Luizinho")); // Verifica se o segundo nome na lista de filhos é "Luizinho"
    }
    
    @Test 
    public void devoFazerPesquisasAvancadasComXML() { 
        RestAssured.given() 
            .when() 
                .get("/usersXML") 
            .then() 
                .body("users.user.size()", is(3))// Verifica se o tamanho da lista de usuários é 3
                .body("users.user.findAll{it.age.toInteger() <= 25}.size()", is(2))// Verifica o tamanho da lista de usuários com idade menor ou igual a 25
                .body("users.user.findAll{it.age.toInteger() > 25}.size()", is(1))// Verifica o tamanho da lista de usuários com idade maior que 25
                .body("users.user.findAll{it.age.toInteger() == 25}.size()", is(1))// Verifica o tamanho da lista de usuários com idade igual a 25
                .body("users.user.@id", hasItems("1", "2", "3"))// Verifica se os ids dos usuários estão presentes na lista
                .body("users.user.find{it.age == 25}.name", is("Maria Joaquina"))// Verifica o nome do usuário com idade igual a 25
                .body("users.user.findAll{it.name.toString().contains('n')}.name", hasItems("Maria Joaquina", "Ana Julia"))// Verifica se os nomes dos usuários contêm a letra 'n'
                .body("users.user.findAll{it.name.toString().length() > 10}.name", hasItems("Maria Joaquina", "João da Silva"))// Verifica se os nomes dos usuários têm mais de 10 caracteres
                .body("users.user.salary.find{it != null}", is("1234.5678"))// Verifica o salário do usuário que não é nulo
                .body("user.user.age.collect{it.toInteger() * 2}", hasItems(40, 50, 60))// Verifica se o dobro da idade dos usuários está presente na lista
                .body("user.user.name.findAll{it.toString().startsWith('Maria')}.collect{it.toString().toUpperCase()}", is("MARIA JOAQUINA"));// Verifica se o nome dos usuários que começam com 'Maria' em maiúsculas
    }
    
    @Test 
    public void devoFazerPesquisasAvancadasComXMLEJava() { 
        String nome = RestAssured.given() 
            .when() 
                .get("/usersXML")
            .then() 
                .statusCode(200) 
                .extract().path("user.user.name.findAll{it.toString().startsWith('Maria')}"); // Extrai o caminho para encontrar o nome que começa com 'Maria'
        Assert.assertEquals("Maria Joaquina", nome); // Verifica se o nome é "Maria Joaquina"
        Assert.assertEquals("MARIA JOAQUINA", nome.toUpperCase()); // Verifica se o nome em maiúsculas é "MARIA JOAQUINA"
    }
    
	@Test 
public void devoFazerPesquisasAvancadasComXMLEJavaUtilizandoContains() {
    ArrayList<NodeImpl> nomes = RestAssured.given()
        .when() 
            .get("/usersXML") 
        .then() 
            .statusCode(200)
            .extract().path("users.user.name.findAll{it.toString().contains('n')}"); // Extrai o caminho para encontrar os nomes que contêm a letra 'n'
            
            Assert.assertEquals(2, nomes.size()); // Verifica se o tamanho da lista de nomes é 2
            Assert.assertEquals("Maria Joaquina".toUpperCase(), nomes.get(0).toString().toUpperCase()); // Verifica se o primeiro nome na lista é "Maria Joaquina" em maiúsculas
            Assert.assertEquals("Ana Julia".toUpperCase(), nomes.get(1).toString().toUpperCase()); // Verifica se o segundo nome na lista é "Ana Julia" em maiúsculas
}

@Test 
public void devoFazerPesquisasAvancadasComXPath() {
    RestAssured.given()
        .when() 
            .get("/usersXML") 
        .then() 
            .statusCode(200)
            .body(hasXPath("count(/users/user)", is("3")))
            .body(hasXPath("//user[@id = '1']")) // Verifica a existência de um elemento <user> com id igual a '1'
            .body(hasXPath("//name[text() = 'Luizinho']/../../name", is("Ana Julia"))) // Verifica se o nome 'Luizinho' está associado ao nome 'Ana Julia'
            .body(hasXPath("//name[text() = 'Ana Julia']/following-sibling::filhos", allOf(containsString("Zezinho"), containsString("Luizinho")))) // Verifica se os filhos de 'Ana Julia' contêm 'Zezinho' e 'Luizinho'
            .body(hasXPath("/users/user/name", is("João da Silva"))) // Verifica se o nome é 'João da Silva'
            .body(hasXPath("/users/user[2]/name", is("Maria Joaquina"))) // Verifica se o segundo nome na lista de usuários é 'Maria Joaquina'
            .body(hasXPath("/users/user[last()]/name", is("Ana Julia"))) // Verifica se o último nome na lista de usuários é 'Ana Julia'
            .body(hasXPath("count(/users/user/name[contains(., 'n')])", is("2"))) // Verifica a contagem de nomes que contêm a letra 'n'
            .body(hasXPath("//user[age < 24]/name", is("Ana Julia"))) // Verifica se o nome é 'Ana Julia' para usuários com idade inferior a 24
            .body(hasXPath("//user[age > 20 and age < 30]/name", is("Maria Joaquina"))); // Verifica se o nome é 'Maria Joaquina' para usuários com idade entre 20 e 30
}
    
}
