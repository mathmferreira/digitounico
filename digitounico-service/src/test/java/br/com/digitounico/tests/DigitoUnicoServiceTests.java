package br.com.digitounico.tests;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.digitounico.dto.DigitoUnicoDTO;
import br.com.digitounico.dto.UsuarioDTO;
import br.com.digitounico.services.DigitoUnicoService;
import br.com.digitounico.services.UsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class DigitoUnicoServiceTests {

	@Autowired
	private DigitoUnicoService service;
	
	@Autowired
	private UsuarioService usuarioService;
	
	private UsuarioDTO usuario;

	@Test
	public void testACalcularDigitoUnicoSemUsuario() {
		DigitoUnicoDTO dto = DigitoUnicoDTO.builder().numero("9875").concatenacao(4).build();
		dto = service.save(dto);
		assertNotEquals(null, dto);
		assertNotEquals(null, dto.getId());
	}
	
	@Test
	public void testBCalcularDigitoUnicoUsuario() {
		usuario = UsuarioDTO.builder().nome("Matheus Ferreira").email("mathmferreira@gmail.com").criptografado(false).build();
		usuario = usuarioService.save(usuario);
		assertNotEquals(null, usuario);
		assertNotEquals(null, usuario.getId());
		
		DigitoUnicoDTO dto = DigitoUnicoDTO.builder().numero("9875").concatenacao(4).idUsuario(usuario.getId()).build();
		dto = service.save(dto);
		assertNotEquals(null, dto);
		assertNotEquals(null, dto.getId());
	}

	@Test
	public void testCFindByUsuario() {
		List<DigitoUnicoDTO> dtos = service.findByUsuario(usuario.getId());
		assertNotEquals(null, dtos);
	}
	
}
