package br.com.digitounico.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.digitounico.dto.DigitoUnicoDTO;
import br.com.digitounico.dto.UsuarioDTO;
import br.com.digitounico.services.UsuarioService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class DigitoUnicoControllerTests {

	private ObjectMapper mapper = new ObjectMapper();
	private RequestSpecification given = RestAssured.given().contentType(ContentType.JSON);
	private static final String PATH = "/digitounico";
	private UsuarioDTO usuarioDTO;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@LocalServerPort
	private int port;
	
	@Before
	public void init() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.basePath = "/api";
		RestAssured.port = port;
		this.usuarioDTO = usuarioService.save(
				UsuarioDTO.builder().nome("Matheus").email("mathmferreira@hotmail.com").criptografado(false).build());
	}
	
	@Test
	public void testACalcularDigitoUnicoSemUsuario() throws JsonProcessingException {
		DigitoUnicoDTO dto = DigitoUnicoDTO.builder().numero("9875").concatenacao(4).build();
		given.body(mapper.writeValueAsString(dto)).post(PATH + "/calcular").then().statusCode(200);
	}
	
	@Test
	public void testBCalcularDigitoUnicoUsuario() throws JsonProcessingException {
		DigitoUnicoDTO dto = DigitoUnicoDTO.builder().numero("9875").concatenacao(4).idUsuario(usuarioDTO.getId()).build();
		given.body(mapper.writeValueAsString(dto)).post(PATH + "/calcular").then().statusCode(200);
	}
	
	@Test
	public void testCFindByUsuario() {
		given.get(PATH + "/{idUsuario}", usuarioDTO.getId()).then().statusCode(200);
	}
	
}
