package br.univille.apidacs2022.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.univille.apidacs2022.service.PacienteService;
import br.univille.coredacs2022.entity.Paciente;
import br.univille.coredacs2022.repository.PacienteRepository;


@Service
public class PacienteServiceImpl implements PacienteService{


    private PacienteRepository repository;

    @Override
    public List<Paciente> getAll() {
        return repository.findAll();
    }

    @Override
    public Paciente save(Paciente paciente) {
        repository.save(paciente);
        return paciente;
    }

    @Override
    public Paciente findById(long id) {
        return null;
    }

    @Override
    public Paciente delete(long id) {
        return null;
    }
    
}
