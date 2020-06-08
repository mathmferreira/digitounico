package br.com.digitounico.test;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DigitoUnicoControllerTests {

	private ObjectMapper mapper = new ObjectMapper();
	private static final String PATH = "/digitounico";
	private UsuarioDTO usuarioDTO;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@LocalServerPort
	private int port;
	
	@Before
	public void init() {
		RestAssured.baseURI = RestAssured.DEFAULT_URI;
		RestAssured.basePath = "/api";
		RestAssured.port = port;
		this.usuarioDTO = usuarioService.save(
				UsuarioDTO.builder().nome("Matheus").email("mathmferreira@hotmail.com").criptografado(false).build());
	}
	
	@Test
	public void testACalcularDigitoUnicoSemUsuario() throws JsonProcessingException {
		DigitoUnicoDTO dto = DigitoUnicoDTO.builder().numero("9875").concatenacao(4).build();
		RestAssured.given().contentType(ContentType.JSON).body(mapper.writeValueAsString(dto)).post(PATH + "/calcular").then().statusCode(200);
	}
	
	@Test
	public void testBCalcularDigitoUnicoUsuario() throws JsonProcessingException {
		DigitoUnicoDTO dto = DigitoUnicoDTO.builder().numero("9875").concatenacao(4).idUsuario(usuarioDTO.getId()).build();
		RestAssured.given().contentType(ContentType.JSON).body(mapper.writeValueAsString(dto)).post(PATH + "/calcular").then().statusCode(200);
	}
	
	@Test
	public void testCFindByUsuario() {
		RestAssured.given().contentType(ContentType.JSON).get(PATH + "/usuario/{idUsuario}", usuarioDTO.getId()).then().statusCode(200);
	}
	
}
