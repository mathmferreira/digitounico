package br.com.digitounico.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder @Getter @Setter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(callSuper = false)
public class DigitoUnicoDTO extends DTO {

	private Long id;
	private String numero;
	private Integer concatenacao;
	private Integer digitoUnicoGerado;
	private Long idUsuario;

}
