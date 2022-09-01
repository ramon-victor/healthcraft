package br.univille.coredacs2022.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.univille.coredacs2022.entity.Medico;


@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    List<Medico> findByNomeIgnoreCaseContaining(@Param("nome")String nome);
}