package br.com.digitounico.controllers;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.digitounico.dto.BaseDTO;
import br.com.digitounico.entities.Persistable;
import br.com.digitounico.services.AbstractService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractController<T extends Persistable<PK>, DTO extends BaseDTO, PK extends Serializable> {

	protected abstract AbstractService<T, DTO, PK> getService();

	@GetMapping(value = "/")
	@ResponseStatus(code = HttpStatus.OK)
	public List<DTO> findAll() {
		log.debug(">>> AbstractController.findAll {}");
		List<DTO> lista = getService().findAll();
		log.debug("<<< AbstractController.findAll [lista={}]", lista);
		return lista;
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public DTO findById(@PathVariable PK id) {
		log.debug(">>> AbstractController.findById [id={}]", id);
		DTO dto = getService().findById(id);
		log.debug("<<< AbstractController.findById [id={}, dto={}]", id, dto);
		return dto;
	}
	
	@PostMapping(value = "/")
	@ResponseStatus(code = HttpStatus.CREATED)
	public DTO create(@Valid @RequestBody DTO dto) {
		log.debug(">>> AbstractController.create [dto={}]", dto);
		dto = getService().save(dto);
		log.debug("<<< AbstractController.create [dto={}]", dto);
		return dto;
	}
	
	@PutMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public DTO udpate(@PathVariable PK id, @Valid @RequestBody DTO dto) {
		log.debug(">>> AbstractController.update [id={}, dto={}] ", id, dto);
		dto = getService().update(id, dto);
		log.debug("<<< AbstractController.update [id={}, dto={}] ", id, dto);
		return dto;
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> deleteById(@PathVariable PK id) {
		log.debug(">>> AbstractController.deleteById [id={}]", id);
		getService().delete(id);
		log.debug("<<< AbstractController.deleteById [id={}]", id);
		return ResponseEntity.ok("Entidade excluída com sucesso");
	}

}
