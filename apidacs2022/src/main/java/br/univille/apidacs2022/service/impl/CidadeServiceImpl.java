package br.univille.apidacs2022.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.apidacs2022.service.CidadeService;
import br.univille.coredacs2022.entity.Cidade;
import br.univille.coredacs2022.repository.CidadeRepository;

@Service
public class CidadeServiceImpl implements CidadeService {

    @Autowired
    CidadeRepository repository;


    @Override
    public List<Cidade> getAll() {
        return repository.findAll();
    }

    @Override
    public Cidade save(Cidade cidade) {
        repository.save(cidade);
        return cidade;
    }

    @Override
    public Cidade findById(long id) {
        Optional<Cidade> cidade = repository.findById(id);
        if(cidade.isPresent()) {
            return cidade.get();
        }
        return null;
    }

    @Override
    public Cidade delete(long id) {
        Optional<Cidade> cidade = repository.findById(id);
        if(cidade.isPresent()) {
            repository.delete(cidade.get());
        }
        return null;
    }


    @Override
    public List<Cidade> getByName(String nome) {
        return repository.findByNomeIgnoreCaseContaining(nome);
    }
    
}
