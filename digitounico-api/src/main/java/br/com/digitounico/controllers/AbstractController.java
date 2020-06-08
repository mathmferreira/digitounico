package br.com.digitounico.controllers;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractController<T extends Persistable<PK>, DTO extends BaseDTO, PK extends Serializable> {

	protected abstract AbstractService<T, DTO, PK> getService();

	@ApiOperation(value = "Listar todas as entidades", nickname = "create")
	@ApiResponse(code = 200, message = "Listagem das entidades")
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public List<DTO> findAll() {
		log.debug(">>> AbstractController.findAll {}");
		List<DTO> lista = getService().findAll();
		log.debug("<<< AbstractController.findAll [lista={}]", lista);
		return lista;
	}
	
	@ApiOperation(value = "Encontrar a entidade através do ID", nickname = "findById")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Entidade encontrada"),
		@ApiResponse(code = 404, message = "Entidade não encontrada")
	})
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public DTO findById(@PathVariable PK id) {
		log.debug(">>> AbstractController.findById [id={}]", id);
		DTO dto = getService().findById(id);
		log.debug("<<< AbstractController.findById [id={}, dto={}]", id, dto);
		return dto;
	}
	
	@ApiOperation(value = "Criar nova entidade", nickname = "findAll")
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "Entidade criada com sucesso"),
		@ApiResponse(code = 400, message = "Campos mal preenchidos")
	})
	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public DTO create(@Valid @RequestBody DTO dto) {
		log.debug(">>> AbstractController.create [dto={}]", dto);
		dto = getService().save(dto);
		log.debug("<<< AbstractController.create [dto={}]", dto);
		return dto;
	}
	
	@ApiOperation(value = "Alterar uma entidade pelo ID", nickname = "update")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Entidade alterada com sucesso"),
		@ApiResponse(code = 400, message = "Campos mal preenchidos"),
		@ApiResponse(code = 404, message = "Entidade não encontrada")
	})
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public DTO udpate(@PathVariable PK id, @Valid @RequestBody DTO dto) {
		log.debug(">>> AbstractController.update [id={}, dto={}] ", id, dto);
		dto = getService().update(id, dto);
		log.debug("<<< AbstractController.update [id={}, dto={}] ", id, dto);
		return dto;
	}
	
	@ApiOperation(value = "Excluir uma entidade pelo ID", nickname = "delete")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Entidade excluída com sucesso"),
		@ApiResponse(code = 404, message = "Entidade não encontrada")
	})
	@DeleteMapping(value = "/{id}", produces = {MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> deleteById(@PathVariable PK id) {
		log.debug(">>> AbstractController.deleteById [id={}]", id);
		getService().delete(id);
		log.debug("<<< AbstractController.deleteById [id={}]", id);
		return ResponseEntity.ok("Entidade excluída com sucesso");
	}

}
