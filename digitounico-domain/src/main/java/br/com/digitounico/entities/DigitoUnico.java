package br.com.digitounico.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.digitounico.utils.Nomenclatura;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Nomenclatura.TABELA + "digito_unico")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DigitoUnico extends AuditableEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "digito_unico" + Nomenclatura.SEQUENCIA)
	@SequenceGenerator(name = "digito_unico" + Nomenclatura.SEQUENCIA, sequenceName = "digito_unico_id" + Nomenclatura.SEQUENCIA, allocationSize = 1)
	@Column(name = Nomenclatura.CHAVE_PRIMARIA + "digito_unico", nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = Nomenclatura.CHAVE_PRIMARIA + "usuario", foreignKey = @ForeignKey(name = Nomenclatura.CHAVE_ESTRANGEIRA + "digito_unico_usuario"))
	private Usuario usuario;
	
	@NotBlank
	@Column(name = Nomenclatura.DESCRICAO + "numero", nullable = false)
	private String numero;
	
	@NotNull @Min(1) @Max(100000)
	@Column(name = Nomenclatura.NUMERICO + "concatenacao", nullable = false)
	private Integer concatenacao;
	
	@Column(name = Nomenclatura.NUMERICO + "digito_unico_gerado", nullable = false)
	private Integer digitoUnicoGerado;
	
}
