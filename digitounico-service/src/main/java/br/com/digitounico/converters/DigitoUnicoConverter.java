package br.com.digitounico.converters;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.digitounico.dto.DigitoUnicoDTO;
import br.com.digitounico.entities.DigitoUnico;
import br.com.digitounico.entities.Usuario;

@Component
public class DigitoUnicoConverter implements Converter<DigitoUnico, DigitoUnicoDTO> {

	@Override
	public DigitoUnico convertToEntity(DigitoUnicoDTO dto) {
		DigitoUnico digito = new DigitoUnico();
		BeanUtils.copyProperties(dto, digito);
		
		if (dto.getIdUsuario() != null) {			
			digito.setUsuario(new Usuario());
			digito.getUsuario().setId(dto.getIdUsuario());
		}
		
		return digito;
	}

	@Override
	public DigitoUnicoDTO convertToDTO(DigitoUnico entity) {
		DigitoUnicoDTO dto = DigitoUnicoDTO.builder().build();
		BeanUtils.copyProperties(entity, dto);
		dto.setIdUsuario(Optional.ofNullable(entity).map(DigitoUnico::getUsuario).map(Usuario::getId).orElse(null));
		return dto;
	}
	
}
