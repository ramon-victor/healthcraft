package br.univille.apidacs2022.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.univille.coredacs2022.entity.Paciente;
import br.univille.coredacs2022.repository.PacienteRepository;

@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteControllerAPI {

    @Autowired
    private PacienteRepository repository;

    @GetMapping
    public ResponseEntity<List<Paciente>> getAll() {
        var listaPacientes = repository.findAll();
        return new ResponseEntity<List<Paciente>>(listaPacientes, HttpStatus.OK);
    }
}
