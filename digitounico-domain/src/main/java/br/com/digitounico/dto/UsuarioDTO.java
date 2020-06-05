package br.com.digitounico.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
public class UsuarioDTO extends BaseDTO {

	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank @Email
	private String email;
	
	private List<DigitoUnicoDTO> digitosUnicos;

}
