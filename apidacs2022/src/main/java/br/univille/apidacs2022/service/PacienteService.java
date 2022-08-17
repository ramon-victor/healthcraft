package br.univille.apidacs2022.service;

import java.util.List;

import br.univille.coredacs2022.entity.Paciente;

public interface PacienteService {
    public List<Paciente> getAll();
    public Paciente save(Paciente paciente);
    public Paciente findById(long id);
    public Paciente delete(long id);
    public List<Paciente> getByName(String nome);
}

