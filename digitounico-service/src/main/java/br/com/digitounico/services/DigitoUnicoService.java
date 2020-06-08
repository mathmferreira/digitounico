package br.com.digitounico.services;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.CharUtils;
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
import br.com.digitounico.utils.CacheUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	
	public List<DigitoUnicoDTO> findByUsuario(Long id) {
		log.debug(">>> DigitoUnicoService.findByUsuario [id={}]", id);
		List<DigitoUnicoDTO> dtos = repository.findByUsuario(id).parallelStream().map(converter::convertToDTO).collect(Collectors.toList());
		log.debug("<<< DigitoUnicoService.findByUsuario [id={}, lista={}]", id, dtos);
		return dtos;
	}

	public DigitoUnicoDTO calcularDigitoUnico(DigitoUnicoDTO dto) {
		log.debug(">>> DigitoUnicoService.calcularDigitoUnico [dto={}]", dto);
		
		Integer cache = CacheUtils.search(dto);
		
		if (cache != null) {
			dto.setDigitoUnicoGerado(cache);
		} else {
			StringBuilder digito = new StringBuilder();
		
			for (int i = 0; i < dto.getConcatenacao(); i++) {
				digito.append(dto.getNumero());
			}
		
			dto.setDigitoUnicoGerado(somarDigitos(digito.toString()));
			CacheUtils.addToCache(dto);
		}
		
		dto = super.save(dto);

		log.debug("<<< DigitoUnicoService.calcularDigitoUnico [dto={}]", dto);
		return dto;
	}

	private Integer somarDigitos(String digito) {
		Integer result = digito.chars().mapToObj(c -> CharUtils.toIntValue((char) c)).reduce(0, Integer::sum);
		return result.intValue() > 9 ? somarDigitos(result.toString()) : result;
	}

}
