package br.com.digitounico.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import br.com.digitounico.utils.Nomenclatura;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = Nomenclatura.TABELA + "usuario")
@Data @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@SuppressWarnings("serial")
public class Usuario extends AuditableEntity<Long> {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario" + Nomenclatura.SEQUENCIA)
	@SequenceGenerator(name = "usuario" + Nomenclatura.SEQUENCIA, sequenceName = "usuario_id" + Nomenclatura.SEQUENCIA, allocationSize = 1)
	@Column(name = Nomenclatura.CHAVE_PRIMARIA + "usuario", nullable = false)
	private Long id;
	
	@NotBlank
	@Column(name = Nomenclatura.DESCRICAO + "nome", nullable = false, length = 2000)
	private String nome;
	
	@NotBlank
	@Column(name = Nomenclatura.DESCRICAO + "email", nullable = false, length = 2000)
	private String email;
	
	@Column(name = Nomenclatura.LOGICO + "criptografado", nullable = false)
	private boolean criptografado;
	
}
