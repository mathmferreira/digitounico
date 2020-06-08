package br.com.digitounico.converters;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.digitounico.dto.UsuarioDTO;
import br.com.digitounico.entities.Usuario;
import br.com.digitounico.utils.SecurityUtils;

@Component
public class UsuarioConverter implements Converter<Usuario, UsuarioDTO> {

	@Override
	public Usuario convertToEntity(UsuarioDTO dto) {
		if (dto.isCriptografado()) {
			dto = SecurityUtils.criptografar(dto);
		}
		Usuario usuario = new Usuario();
		BeanUtils.copyProperties(dto, usuario);
		return usuario;
	}

	@Override
	public UsuarioDTO convertToDTO(Usuario entity) {
		UsuarioDTO dto = UsuarioDTO.builder().build();
		BeanUtils.copyProperties(entity, dto);
		if (dto.isCriptografado()) {
			dto = SecurityUtils.descriptografar(dto);
		}
		return dto;
	}

}
