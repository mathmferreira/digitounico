package br.com.digitounico.converters;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.digitounico.dto.DigitoUnicoDTO;
import br.com.digitounico.entities.DigitoUnico;

@Component
public class DigitoUnicoConverter implements Converter<DigitoUnico, DigitoUnicoDTO> {

	@Override
	public DigitoUnico convertToEntity(DigitoUnicoDTO dto) {
		DigitoUnico digito = new DigitoUnico();
		BeanUtils.copyProperties(dto, digito);
		return digito;
	}

	@Override
	public DigitoUnicoDTO convertToDTO(DigitoUnico entity) {
		DigitoUnicoDTO dto = DigitoUnicoDTO.builder().build();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}
	
}
