package br.univille.apidacs2022.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.apidacs2022.service.PacienteService;
import br.univille.coredacs2022.entity.Paciente;
import br.univille.coredacs2022.repository.PacienteRepository;


@Service
public class PacienteServiceImpl implements PacienteService{

    @Autowired
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
        Optional<Paciente> paciente = repository.findById(id);
        if(paciente.isPresent()) {
            return paciente.get();
        }
        return null;
    }

    @Override
    public Paciente delete(long id) {
        Optional<Paciente> paciente = repository.findById(id);
        if(paciente.isPresent()) {
            repository.delete(paciente.get());
        }
        return null;
    }

    @Override
    public List<Paciente> getByName(String nome) {
        return repository.findByNomeIgnoreCaseContaining(nome);
    }
    
}
