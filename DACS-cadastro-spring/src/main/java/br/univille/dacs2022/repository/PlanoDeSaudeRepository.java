package br.univille.dacs2022.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.univille.dacs2022.entity.PlanoDeSaude;

@Repository
public interface PlanoDeSaudeRepository extends JpaRepository<PlanoDeSaude,Long>{

}