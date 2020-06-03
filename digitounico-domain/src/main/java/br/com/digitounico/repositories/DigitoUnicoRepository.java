package br.com.digitounico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.digitounico.entities.Usuario;

@Repository
public interface DigitoUnicoRepository extends JpaRepository<Usuario, Long> {

}
