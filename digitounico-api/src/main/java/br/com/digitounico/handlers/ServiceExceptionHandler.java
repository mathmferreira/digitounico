package br.com.digitounico.handlers;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ServiceExceptionHandler {

	private static final String NOT_FOUND = "Entidade não encontrada";
	
	@ResponseBody
	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = NOT_FOUND)
	public String handleException(HttpServletRequest request, EntityNotFoundException exception) {
		log.error(NOT_FOUND + " {} ", exception.getMessage());
		return NOT_FOUND;
	}
	
	@ResponseBody
	@ExceptionHandler(EmptyResultDataAccessException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = NOT_FOUND)
	public String handleException(HttpServletRequest request, EmptyResultDataAccessException exception) {
		log.error(NOT_FOUND + " {} ", exception.getMessage());
		return NOT_FOUND;
	}
	
}
