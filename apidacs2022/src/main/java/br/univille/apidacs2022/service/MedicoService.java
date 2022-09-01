package br.univille.apidacs2022.service;

import java.util.List;

import br.univille.coredacs2022.entity.Medico;

public interface MedicoService {
    public List<Medico> getAll();
    public Medico save(Medico medico);
    public Medico findById(long id);
    public Medico delete(long id);
    public List<Medico> getByName(String nome);
}