package br.univille.dacs2022.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import br.univille.dacs2022.dto.MedicoDTO;
import br.univille.dacs2022.entity.Medico;

@Mapper
public interface MedicoMapper {
    List<MedicoDTO> mapListMedico(List<Medico> medico);
    List<Medico> mapListMedicoDTO(List<MedicoDTO> medico);

    MedicoDTO mapMedico(Medico medico);
    Medico mapMedicoDTO(MedicoDTO medico);
}
