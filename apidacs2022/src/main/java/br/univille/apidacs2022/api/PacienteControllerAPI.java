package br.univille.apidacs2022.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.univille.apidacs2022.service.PacienteService;
import br.univille.coredacs2022.entity.Paciente;

@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteControllerAPI {

    @Autowired
    private PacienteService service;

    @GetMapping
    public ResponseEntity<List<Paciente>> getAll() {
        var listaPacientes = service.getAll();
        return new ResponseEntity<List<Paciente>>(listaPacientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getById(@PathVariable("id") long id) {

        var paciente = service.findById(id);
        return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Paciente> insertPaciente(@RequestBody Paciente paciente) {
        if (paciente.getId() == 0) {
            service.save(paciente);
            return new ResponseEntity<Paciente>(paciente, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Paciente>> getByNome(@PathVariable("nome") String nome) {
        var listaPacientes = service.getByName(nome);
        return new ResponseEntity<List<Paciente>>(listaPacientes, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> update(@PathVariable("id") long id,
            @RequestBody Paciente paciente) {

        var pacienteAntigo = service.findById(id);
        if (pacienteAntigo == null) {
            return ResponseEntity.notFound().build();
        }

        pacienteAntigo.setNome(paciente.getNome());
        pacienteAntigo.setSexo(paciente.getSexo());
        pacienteAntigo.setDataNascimento(paciente.getDataNascimento());
        pacienteAntigo.setCidade(paciente.getCidade());
        pacienteAntigo.setListaPlanos(paciente.getListaPlanos());

        service.save(pacienteAntigo);

        return new ResponseEntity<Paciente>(pacienteAntigo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Paciente> update(@PathVariable("id") long id) {

        var pacienteAntigo = service.findById(id);
        if (pacienteAntigo == null) {
            return ResponseEntity.notFound().build();
        }

        service.delete(id);

        return new ResponseEntity<Paciente>(pacienteAntigo, HttpStatus.OK);
    }
}
