package br.univille.apidacs2022.service;

import java.util.List;

import br.univille.coredacs2022.entity.Procedimento;

public interface ProcedimentoService {
    public List<Procedimento> getAll();
    public Procedimento save(Procedimento procedimento);
    public Procedimento findById(long id);
    public Procedimento delete(long id);
    public List<Procedimento> getByDescricao(String descricao);
}
