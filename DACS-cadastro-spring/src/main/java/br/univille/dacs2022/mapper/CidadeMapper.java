package br.univille.dacs2022.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import br.univille.dacs2022.dto.CidadeDTO;
import br.univille.coredacs2022.entity.Cidade;

@Mapper(componentModel = "spring")
public interface CidadeMapper {
    List<CidadeDTO> mapListCidade(List<Cidade> cidade);
    List<Cidade> mapListCidadeDTO(List<CidadeDTO> cidade);

    CidadeDTO mapCidade(Cidade cidade);
    Cidade mapCidadeDTO(CidadeDTO cidade);
}
