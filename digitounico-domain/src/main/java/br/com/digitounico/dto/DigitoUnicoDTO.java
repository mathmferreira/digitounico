package br.com.digitounico.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonView;

import br.com.digitounico.view.View;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@JsonInclude(content = Include.NON_NULL)
@SuppressWarnings("serial")
public class DigitoUnicoDTO implements BaseDTO {

	@EqualsAndHashCode.Include
	@JsonView(View.IgnoreUsuario.class)
	private Long id;
	
	@JsonView(View.IgnoreUsuario.class)
	@NotBlank
	@Pattern(regexp = "^[0-9]+$", message = "O digito informado não é um número")
	private String numero;
	
	@JsonView(View.IgnoreUsuario.class)
	@NotNull @Min(1) @Max(100000)
	private Integer concatenacao;
	
	@JsonView(View.IgnoreUsuario.class)
	private Integer digitoUnicoGerado;
	
	private Long idUsuario;

}
