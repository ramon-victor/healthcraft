package br.univille.dacs2022.mapper;

import java.util.List;
import br.univille.dacs2022.dto.PlanoDeSaudeDTO;
import br.univille.dacs2022.entity.PlanoDeSaude;

public interface PlanoDeSaudeMapper {
    List<PlanoDeSaudeDTO> mapListPlanoDeSaude(List<PlanoDeSaude> planoDeSaude);
    List<PlanoDeSaude> mapListPlanoDeSaudeDTO(List<PlanoDeSaudeDTO> planoDeSaude);

    PlanoDeSaudeDTO mapPlanoDeSaude(PlanoDeSaude planoDeSaude);
    PlanoDeSaude mapPlanoDeSaudeDTO(PlanoDeSaudeDTO planoDeSaude);
}
