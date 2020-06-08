package br.com.digitounico.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.digitounico.dto.UsuarioDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class UsuarioControllerTests {

	private ObjectMapper mapper = new ObjectMapper();
	private RequestSpecification given = RestAssured.given().contentType(ContentType.JSON);
	private static final String PATH = "/usuario";
	
	@LocalServerPort
	private int port;
	
	@Before
	public void init() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.basePath = "/api";
		RestAssured.port = port;
	}
	
	@Test
	public void testACreate() throws JsonProcessingException {
		UsuarioDTO dto = UsuarioDTO.builder().nome("Matheus").email("mathmferreira@hotmail.com").criptografado(false).build();
		given.body(mapper.writeValueAsString(dto)).post(PATH).then().statusCode(201);
	}
	
	@Test
	public void testBFindById() {
		given.get(PATH + "/{id}", 1).then().statusCode(200);
	}
	
	@Test
	public void testCFindAll() {
		given.get(PATH).then().statusCode(200);
	}
	
	@Test
	public void testDUpdate() throws JsonProcessingException {
		UsuarioDTO dto = UsuarioDTO.builder().nome("Matheus Ferreira").email("mathmferreira@gmail.com").criptografado(false).build();
		given.body(mapper.writeValueAsString(dto)).put(PATH + "/{id}", 1).then().statusCode(200);
	}

	@Test
	public void testECriptografar() {
		given.post(PATH + "/{id}/criptografar", 1).then().statusCode(200);
	}
	
	@Test
	public void testFDelete() {
		given.delete(PATH + "/{id}", 1).then().statusCode(200);
	}
	
}
