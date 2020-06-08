package br.com.digitounico.services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.digitounico.converters.Converter;
import br.com.digitounico.dto.BaseDTO;
import br.com.digitounico.entities.Persistable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractService<T extends Persistable<PK>, DTO extends BaseDTO, PK extends Serializable> {

	protected abstract JpaRepository<T, PK> getRepository();

	protected abstract Converter<T, DTO> getConverter();

	/**
	 * Pesquisa uma entidade através do id
	 * 
	 * @param id - o id que representa a entidade
	 * @return o objeto representando a entidade
	 */
	public DTO findById(PK id) {
		log.debug(">>> AbstractService.findById [id={}] ", id);
		Optional<T> entity = getRepository().findById(id);
		log.debug("<<< AbstractService.findById [id={}, entity={}] ", id, entity);
		Optional<DTO> dto = entity.isPresent() ? Optional.ofNullable(getConverter().convertToDTO(entity.get())) : Optional.empty();
		log.debug("<<< AbstractService.findById [id={}, entity={}, dto={}] ", id, entity, dto);
		return dto.orElseThrow(EntityNotFoundException::new);
	}

	/**
	 * Busca todos os registros da entidade
	 * 
	 * @return a lista com todos os objetos encontrados
	 */
	public List<DTO> findAll() {
		List<T> entities = getRepository().findAll();
		log.debug("<<< AbstractService.findAll [entities={}] ", entities);
		List<DTO> dtos = entities.parallelStream().map(entity -> getConverter().convertToDTO(entity)).collect(Collectors.toList());
		log.debug("<<< AbstractService.findAll [entities={}] ", entities);
		return dtos;
	}

	/**
	 * Salva uma nova entidade no banco
	 * 
	 * @param dto - o DTO correspondente a entidade a ser criada
	 * @return o DTO da entidade correspondente salva no banco
	 */
	public DTO save(DTO dto) {
		log.debug(">>> AbstractService.save [dto={}] ", dto);
		T entity = getConverter().convertToEntity(dto);
		log.debug(">>> AbstractService.save [entity={}, dto={}] ", entity, dto);
		T t = getRepository().save(entity);
		log.debug("<<< AbstractService.save [entity={}] ", t);
		dto = getConverter().convertToDTO(t);
		log.debug("<<< AbstractService.save [dto={}] ", dto);
		return dto;
	}

	/**
	 * Atualiza a entidade de acordo com o DTO recebido
	 * 
	 * @param id     - o id da entidade a ser atualizada
	 * @param entity - o DTO correspondente a entidade com os dados atualizados
	 * @return o DTO da entidade atualizada correspondente
	 */
	public DTO update(PK id, DTO entity) {
		log.debug(">>> AbstractService.update [entity={}] ", entity);
		DTO dto = this.findById(id);
		log.debug("<<< AbstractService.update [dto={}] ", dto);
		dto = getConverter().convertToClone(entity, dto);
		log.debug("<<< AbstractService.update [dto={}] ", dto);
		T t = getRepository().save(getConverter().convertToEntity(dto));
		log.debug("<<< AbstractService.update [entity={}] ", t);
		entity = getConverter().convertToDTO(t);
		log.debug("<<< AbstractService.update [entity={}] ", entity);
		return entity;
	}

	/**
	 * Exclui uma entidade através do seu id
	 * 
	 * @param id - o id da entidade a ser excluída
	 */
	public void delete(PK id) {
		log.debug(">>> AbstractService.delete [id={}] ", id);
		getRepository().deleteById(id);
		log.debug("<<< AbstractService.delete [id={}] ", id);
	}

}
