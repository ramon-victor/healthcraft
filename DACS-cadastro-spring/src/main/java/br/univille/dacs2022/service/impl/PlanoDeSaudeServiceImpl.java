package br.univille.dacs2022.service.impl;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.dacs2022.dto.PlanoDeSaudeDTO;
import br.univille.dacs2022.mapper.PlanoDeSaudeMapper;
import br.univille.coredacs2022.repository.PlanoDeSaudeRepository;
import br.univille.dacs2022.service.PlanoDeSaudeService;

@Service
public class PlanoDeSaudeServiceImpl implements PlanoDeSaudeService{
    @Autowired
    private PlanoDeSaudeRepository repository;
    private PlanoDeSaudeMapper mapper 
        = Mappers.getMapper(PlanoDeSaudeMapper.class);

    @Override
    public List<PlanoDeSaudeDTO> getAll() {
        var listaPlanos = repository.findAll();
        return mapper.mapListPlanoDeSaude(listaPlanos);
    }

    @Override
    public PlanoDeSaudeDTO getById(long id) {
        var plano = repository.findById(id);
        return mapper.mapPlanoDeSaude(plano.get());
    }

}
