package br.com.digitounico.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.digitounico.dto.DigitoUnicoDTO;
import br.com.digitounico.entities.DigitoUnico;
import br.com.digitounico.services.AbstractService;
import br.com.digitounico.services.DigitoUnicoService;
import br.com.digitounico.view.View;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/digitounico")
public class DigitoUnicoController extends AbstractController<DigitoUnico, DigitoUnicoDTO, Long> {

	@Autowired
	private DigitoUnicoService service;
	
	@Override
	protected AbstractService<DigitoUnico, DigitoUnicoDTO, Long> getService() {
		return service;
	}
	
	@ApiOperation(value = "Encontrar pelo ID do Usuário", nickname = "findByUsuario")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Listagem das entidades"),
		@ApiResponse(code = 404, message = "Usuário não encontrado")
	})
	@JsonView(View.IgnoreUsuario.class)
	@GetMapping(value = "/usuario/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public List<DigitoUnicoDTO> findByUsuario(@PathVariable Long idUsuario) {
		log.debug(">>> DigitoUnicoController.findByUsuario [idUsuario={}]", idUsuario);
		List<DigitoUnicoDTO> dtos = service.findByUsuario(idUsuario);
		log.debug("<<< DigitoUnicoController.findByUsuario [idUsuario={}, lista={}]", idUsuario, dtos);
		return dtos;
	}
	
	@ApiOperation(value = "Calcular o Digito Único de um número", nickname = "calcular")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Digito calculado com sucesso"),
		@ApiResponse(code = 400, message = "Campos mal preenchidos"),
		@ApiResponse(code = 404, message = "Usuário não encontrado")
	})
	@JsonView(View.IgnoreUsuario.class)
	@PostMapping(value = "/calcular", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public DigitoUnicoDTO calcularDigitoUnico(@Valid @RequestBody DigitoUnicoDTO dto) {
		log.debug(">>> DigitoUnicoController.calcularDigitoUnico [dto={}]", dto);
		dto = service.calcularDigitoUnico(dto);
		log.debug("<<< DigitoUnicoController.calcularDigitoUnico [dto={}]", dto);
		return dto;
	}
	
}
