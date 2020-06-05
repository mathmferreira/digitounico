package br.com.digitounico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.digitounico.converters.Converter;
import br.com.digitounico.converters.DigitoUnicoConverter;
import br.com.digitounico.dto.DigitoUnicoDTO;
import br.com.digitounico.entities.DigitoUnico;
import br.com.digitounico.repositories.DigitoUnicoRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class DigitoUnicoService extends AbstractService<DigitoUnico, DigitoUnicoDTO, Long> {

	@Autowired
	private DigitoUnicoRepository repository;
	
	@Autowired
	private DigitoUnicoConverter converter;
	
	@Override
	protected JpaRepository<DigitoUnico, Long> getRepository() {
		return repository;
	}

	@Override
	protected Converter<DigitoUnico, DigitoUnicoDTO> getConverter() {
		return converter;
	}

}
