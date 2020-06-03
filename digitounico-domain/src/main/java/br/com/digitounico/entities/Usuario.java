package br.com.digitounico.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.digitounico.utils.Nomenclatura;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Nomenclatura.TABELA + "usuario")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Usuario extends AuditableEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario" + Nomenclatura.SEQUENCIA)
	@SequenceGenerator(name = "usuario" + Nomenclatura.SEQUENCIA, sequenceName = "usuario_id" + Nomenclatura.SEQUENCIA, allocationSize = 1)
	@Column(name = Nomenclatura.CHAVE_PRIMARIA + "usuario", nullable = false)
	private Long id;
	
	@NotBlank
	@Column(name = Nomenclatura.DESCRICAO + "nome", nullable = false)
	private String nome;
	
	@Email
	@NotBlank
	@Column(name = Nomenclatura.DESCRICAO + "email", nullable = false)
	private String email;
	
}
