package br.com.digitounico.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.digitounico.dto.UsuarioDTO;
import br.com.digitounico.services.UsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class UsuarioServiceTests {

	@Autowired
	private UsuarioService service;

	@Test
	public void testACreate() {
		UsuarioDTO dto = UsuarioDTO.builder().nome("Matheus").email("mathmferreira@hotmail.com").criptografado(false).build();
		dto = service.save(dto);
		assertNotEquals(null, dto);
		assertNotEquals(null, dto.getId());
	}

	@Test
	public void testBFindById() {
		UsuarioDTO dto = service.findById(1L);
		assertNotEquals(null, dto);
		assertEquals(1L, dto.getId());
	}

	@Test
	public void testCFindAll() {
		List<UsuarioDTO> dtos = service.findAll();
		assertNotEquals(null, dtos);
	}

	@Test
	public void testDUpdate() {
		UsuarioDTO dto = UsuarioDTO.builder().nome("Matheus Ferreira").email("mathmferreira@gmail.com").criptografado(false).build();
		UsuarioDTO updated = service.update(1L, dto);
		assertNotEquals(null, updated);
		dto.setId(updated.getId());
		assertEquals(dto, updated);
	}

	@Test
	public void testECriptografar() {
		service.criptografarUsuario(1L);
		assertTrue(service.findAll().parallelStream().anyMatch(u -> u.getId().equals(1L) && u.isCriptografado()));
	}

	@Test
	public void testFDelete() {
		service.delete(1L);
		assertFalse(service.findAll().parallelStream().anyMatch(u -> u.getId().equals(1L)));
	}

}
