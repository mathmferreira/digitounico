package br.com.digitounico.converters;

import org.springframework.beans.BeanUtils;

import br.com.digitounico.dto.BaseDTO;

public interface Converter<T, DTO extends BaseDTO> {

	/**
	 * Converte de um DTO para uma entidade
	 * 
	 * @param dto - o dto a ser convertido
	 * @return a entidade correspondente
	 */
	public T convertToEntity(DTO dto);

	/**
	 * Converte de uma entidade para um DTO
	 * 
	 * @param entity - a entidade a ser convertida
	 * @return o DTO correspondente
	 */
	public DTO convertToDTO(T entity);

	/**
	 * Converter de um DTO para um outro DTO (utilizado no update)
	 * 
	 * @param source - objeto de origem
	 * @param target - objeto de destino
	 * @return o DTO clonado
	 */
	public default DTO convertToClone(DTO source, DTO target) {
		BeanUtils.copyProperties(source, target, "id");
		return target;
	}

}
