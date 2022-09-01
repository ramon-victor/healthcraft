package br.univille.coredacs2022.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.univille.coredacs2022.entity.Cidade;

@Repository
public interface CidadeRepository 
    extends JpaRepository<Cidade,Long> {
    
        List<Cidade> findByNomeIgnoreCaseContaining(@Param("nome")String nome);
}
