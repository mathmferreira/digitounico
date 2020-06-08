package br.com.digitounico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.digitounico.converters.Converter;
import br.com.digitounico.converters.UsuarioConverter;
import br.com.digitounico.dto.UsuarioDTO;
import br.com.digitounico.entities.Usuario;
import br.com.digitounico.repositories.UsuarioRepository;
import br.com.digitounico.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UsuarioService extends AbstractService<Usuario, UsuarioDTO, Long> {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private UsuarioConverter converter;

	@Override
	protected JpaRepository<Usuario, Long> getRepository() {
		return repository;
	}

	@Override
	protected Converter<Usuario, UsuarioDTO> getConverter() {
		return converter;
	}

	public void criptografarUsuario(Long idUsuario) {
		log.debug(">>> UsuarioService.criptografarUsuario [id={}]", idUsuario);
		UsuarioDTO dto = this.update(idUsuario, SecurityUtils.criptografar(this.findById(idUsuario)));
		log.debug("<<< UsuarioService.criptografarUsuario [id={}, dto={}]", idUsuario, dto);
	}

}
