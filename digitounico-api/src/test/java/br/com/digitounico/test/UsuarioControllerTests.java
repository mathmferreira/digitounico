package br.com.digitounico.test;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.digitounico.dto.UsuarioDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioControllerTests {

	private ObjectMapper mapper = new ObjectMapper();
	private static final String PATH = "/usuario/";
	
	@LocalServerPort
	private int port;
	
	@Before
	public void init() {
		RestAssured.baseURI = RestAssured.DEFAULT_URI;
		RestAssured.basePath = "/api";
		RestAssured.port = port;
	}
	
	@Test
	public void testACreate() throws JsonProcessingException {
		UsuarioDTO dto = UsuarioDTO.builder().nome("Matheus").email("mathmferreira@hotmail.com").criptografado(false).build();
		RestAssured.given().contentType(ContentType.JSON).body(mapper.writeValueAsString(dto)).post(PATH).then().statusCode(201);
	}
	
	@Test
	public void testBFindById() {
		RestAssured.given().contentType(ContentType.JSON).get(PATH + "{id}", 1L).then().statusCode(200);
	}
	
	@Test
	public void testCFindAll() {
		RestAssured.given().contentType(ContentType.JSON).get(PATH).then().statusCode(200);
	}
	
	@Test
	public void testDUpdate() throws JsonProcessingException {
		UsuarioDTO dto = UsuarioDTO.builder().nome("Matheus Ferreira").email("mathmferreira@gmail.com").criptografado(false).build();
		RestAssured.given().contentType(ContentType.JSON).body(mapper.writeValueAsString(dto)).put(PATH + "{id}", 1L).then().statusCode(200);
	}

	@Test
	public void testECriptografar() {
		RestAssured.given().contentType(ContentType.JSON).post(PATH + "{id}/criptografar", 1L).then().statusCode(200);
	}
	
	@Test
	public void testFDelete() {
		RestAssured.given().contentType(ContentType.JSON).delete(PATH + "{id}", 1L).then().statusCode(200);
	}
	
}
