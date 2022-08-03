package br.univille.dacs2022.service.impl;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.dacs2022.dto.MedicoDTO;
import br.univille.dacs2022.entity.Medico;
import br.univille.dacs2022.mapper.MedicoMapper;
import br.univille.dacs2022.repository.medicoRepository;
import br.univille.dacs2022.service.MedicoService;

@Service
public class MedicoServiceImpl implements MedicoService {

    @Autowired
    private medicoRepository repository;

    private MedicoMapper mapper
        = Mappers.getMapper(MedicoMapper.class);
    
    @Override
    public List<MedicoDTO> getAll() {
        return mapper.mapListMedico(repository.findAll());
    }

    @Override
    public MedicoDTO save(MedicoDTO medico) {
        Medico medicoEntity = mapper.mapMedicoDTO(medico);
        medicoEntity = repository.save(medicoEntity);

        return null;
    }

    @Override
    public MedicoDTO findById(long id) {
        Optional<Medico> medicoEntity = repository.findById(id);

        if (medicoEntity.isPresent()) {
            return mapper.mapMedico(medicoEntity.get());
        }
        
        return null;
    }

    @Override
    public MedicoDTO delete(long id) {
        Optional<Medico> medicoEntity = repository.findById(id);

        if (medicoEntity.isPresent()) {
            Medico medico = medicoEntity.get();
            repository.delete(medico);
            return mapper.mapMedico(medicoEntity.get());
        }
        
        return null;
    }
    
}
