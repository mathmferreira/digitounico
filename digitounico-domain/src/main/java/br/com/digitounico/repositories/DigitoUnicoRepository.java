package br.com.digitounico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.digitounico.entities.DigitoUnico;

@Repository
public interface DigitoUnicoRepository extends JpaRepository<DigitoUnico, Long> {

	@Query("Select d From DigitoUnico d Inner Join d.usuario u Where u.id = :idUsuario")
	public List<DigitoUnico> findByUsuario(Long idUsuario);
	
}
