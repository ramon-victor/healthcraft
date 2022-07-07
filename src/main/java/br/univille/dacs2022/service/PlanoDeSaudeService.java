package br.univille.dacs2022.service;

import java.util.List;
import br.univille.dacs2022.dto.PlanoDeSaudeDTO;


public interface PlanoDeSaudeService {
    public List<PlanoDeSaudeDTO> getAll();
    public PlanoDeSaudeDTO getById(long id);
}
