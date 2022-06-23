package br.univille.dacs2022.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import br.univille.dacs2022.dto.ProcedimentoDTO;
import br.univille.dacs2022.entity.Procedimento;

@Mapper
public interface ProcedimentoMapper {
    List<ProcedimentoDTO> mapListProcedimento(List<Procedimento> procedimento);
    List<Procedimento> mapLisProcedimentoDTO(List<ProcedimentoDTO> procedimento);

    ProcedimentoDTO mapProcedimento(Procedimento procedimento);
    Procedimento mapProcedimentoDTO(ProcedimentoDTO procedimento);
}
