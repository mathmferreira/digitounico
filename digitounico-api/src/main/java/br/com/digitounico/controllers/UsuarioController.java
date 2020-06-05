package br.com.digitounico.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digitounico.dto.UsuarioDTO;
import br.com.digitounico.entities.Usuario;
import br.com.digitounico.services.AbstractService;
import br.com.digitounico.services.UsuarioService;

@RestController
@RequestMapping(value = "/api/usuario")
public class UsuarioController extends AbstractController<Usuario, UsuarioDTO, Long> {

	@Autowired
	private UsuarioService service;
	
	@Override
	protected AbstractService<Usuario, UsuarioDTO, Long> getService() {
		return service;
	}

}
