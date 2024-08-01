package br.victorio.rest;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class FileTest {
	
	@Test
	public void deveObrigarEnvioDeArquivo() { 
		given()
			.log().all() // Log de todas as requisições
		.when()
			.post("http://restapi.wcaquino.me/upload") // Envia uma requisição POST para o endpoint de upload
		.then()
			.log().all() // Log de todas as respostas
			.statusCode(404) // Verifica se o código de status é 404 (não encontrado)
			.body("error", Matchers.is("Arquivo não enviado")); // Verifica se a mensagem de erro é "Arquivo não enviado"
	}

	@Test
	public void deveEnviarArquivo() { // Teste para garantir que é possível enviar um arquivo
		given()
			.log().all() // Log de todas as requisições
			.multiPart("arquivo", new File("src/resources/users.pdf")) // Adiciona um arquivo multipart
		.when()
			.post("http://restapi.wcaquino.me/upload") // Envia uma requisição POST para o endpoint de upload
		.then()	
			.log().all() // Log de todas as respostas
			.statusCode(200) // Verifica se o código de status é 200 (OK)
			.body("name", Matchers.is("users.pdf")) // Verifica se o nome do arquivo é "users.pdf"
			.body("size", Matchers.is(102400)); // Verifica se o tamanho do arquivo é 102400 bytes
	}

	@Test
	public void naoDeveEnviarArquivoGrande() { 
		given()
			.log().all() // Log de todas as requisições
			.multiPart("arquivo", new File("src/resources/imagem.jpg")) // Adiciona um arquivo multipart grande
		.when()
			.post("http://restapi.wcaquino.me/upload") // Envia uma requisição POST para o endpoint de upload
		.then()	
			.log().all() // Log de todas as respostas
			.time(Matchers.lessThan(2000L)) // Verifica se o tempo de resposta é inferior a 2000 milissegundos
			.statusCode(413); // Verifica se o código de status é 413 (Solicitação da Entidade Grande)
	}

	@Test
	public void deveRealizarDownloadDeArquivo() throws IOException {
		byte[] image = given()
			.log().all() // Log de todas as requisições
		.when()
			.get("http://restapi.wcaquino.me/download") // Envia uma requisição GET para o endpoint de download
		.then()
			.statusCode(200) // Verifica se o código de status é 200 (OK)
			.extract().asByteArray(); // Extrai a resposta como um array de bytes
		File imagem = new File("src/resources/file.jpg"); // Cria um novo arquivo para armazenar a imagem
		OutputStream out = new FileOutputStream(imagem); // Cria um fluxo de saída para o arquivo
		out.write(image); // Escreve os bytes da imagem no arquivo
		out.close(); // Fecha o fluxo de saída
		System.out.println(image.length); // Imprime o tamanho da imagem
		Assert.assertThat(imagem.length(), Matchers.lessThan(100000L)); // Verifica se o tamanho do arquivo é inferior a 100000 bytes
	}
}
