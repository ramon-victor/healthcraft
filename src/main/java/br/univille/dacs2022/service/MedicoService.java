package br.univille.dacs2022.service;

import java.util.List;
import br.univille.dacs2022.dto.MedicoDTO;

public interface MedicoService {
    public List<MedicoDTO> getAll();
    public MedicoDTO save(MedicoDTO medico);
    public MedicoDTO findById(long id);
    public MedicoDTO delete(long id);
}

