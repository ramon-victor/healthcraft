package br.univille.apidacs2022.service;

import java.util.List;

import br.univille.coredacs2022.entity.Cidade;

public interface CidadeService {
    public List<Cidade> getAll();
    public Cidade save(Cidade cidade);
    public Cidade findById(long id);
    public Cidade delete(long id);
    public List<Cidade> getByName(String nome);
}