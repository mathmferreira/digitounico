package br.com.digitounico.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digitounico.dto.UsuarioDTO;
import br.com.digitounico.entities.Usuario;
import br.com.digitounico.services.AbstractService;
import br.com.digitounico.services.UsuarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/usuario")
public class UsuarioController extends AbstractController<Usuario, UsuarioDTO, Long> {

	@Autowired
	private UsuarioService service;
	
	@Override
	protected AbstractService<Usuario, UsuarioDTO, Long> getService() {
		return service;
	}
	
	@ApiOperation(value = "Criptografa o Nome e Email do Usuário", nickname = "criptografar")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Usuário criptografado com sucesso"),
		@ApiResponse(code = 500, message = "Erro ao criptografar")
	})
	@PostMapping(value = "/{id}/criptografar")
	public ResponseEntity<String> criptografar(@PathVariable Long id) {
		service.criptografarUsuario(id);
		return ResponseEntity.ok("Usuário criptografado com sucesso");
	}

}
