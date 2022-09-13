package br.univille.apidacs2022.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.apidacs2022.service.ProcedimentoService;
import br.univille.coredacs2022.entity.Procedimento;
import br.univille.coredacs2022.repository.ProcedimentoRepository;

@Service
public class ProcedimentoServiceImpl implements ProcedimentoService {

    @Autowired
    ProcedimentoRepository repository;

    @Override
    public List<Procedimento> getAll() {
        return repository.findAll();
    }

    @Override
    public Procedimento save(Procedimento procedimento) {
        repository.save(procedimento);
        return procedimento;
    }

    @Override
    public Procedimento findById(long id) {
        Optional<Procedimento> procedimento = repository.findById(id);
        if (procedimento.isPresent()) {
            return procedimento.get();
        }
        return null;
    }

    @Override
    public Procedimento delete(long id) {
        Optional<Procedimento> procedimento = repository.findById(id);
        if (procedimento.isPresent()) {
            repository.delete(procedimento.get());
        }
        return null;
    }

    @Override
    public List<Procedimento> getByDescricao(String descricao) {
        return repository.findByDescricaoIgnoreCaseContaining(descricao);
    }

}
