package br.com.digitounico.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import br.com.digitounico.utils.Nomenclatura;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class AuditableEntity<PK extends Serializable> implements Persistable<PK> {

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = Nomenclatura.AUDITORIA + "data_criacao", nullable = false, updatable = false)
	private Date dataCriacao;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = Nomenclatura.AUDITORIA + "data_alteracao")
	private Date dataAlteracao;

}
