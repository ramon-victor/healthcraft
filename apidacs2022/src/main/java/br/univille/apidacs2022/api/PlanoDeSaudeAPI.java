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

import br.univille.apidacs2022.service.PlanoDeSaudeService;
import br.univille.coredacs2022.entity.PlanoDeSaude;


@RestController
@RequestMapping("/api/v1/planos")
public class PlanoDeSaudeAPI {
    
    @Autowired
    private PlanoDeSaudeService service;

    @GetMapping
    public ResponseEntity<List<PlanoDeSaude>> getAll() {
        var listaPlanos = service.getAll();
        return new ResponseEntity<List<PlanoDeSaude>>(listaPlanos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanoDeSaude> getById(@PathVariable("id") long id) {

        var planoDeSaude = service.findById(id);
        return new ResponseEntity<PlanoDeSaude>(planoDeSaude, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PlanoDeSaude> insertPlano(@RequestBody PlanoDeSaude planoDeSaude) {
        if (planoDeSaude.getId() == 0) {
            service.save(planoDeSaude);
            return new ResponseEntity<PlanoDeSaude>(planoDeSaude, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<PlanoDeSaude>> getByNome(@PathVariable("nome") String nome) {
        var listaPlanos = service.getByName(nome);
        return new ResponseEntity<List<PlanoDeSaude>>(listaPlanos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanoDeSaude> update(@PathVariable("id") long id,
            @RequestBody PlanoDeSaude planoDeSaude) {

        var planoDeSaudeAntigo = service.findById(id);
        if (planoDeSaudeAntigo == null) {
            return ResponseEntity.notFound().build();
        }

        planoDeSaudeAntigo.setNome(planoDeSaude.getNome());

        service.save(planoDeSaudeAntigo);

        return new ResponseEntity<PlanoDeSaude>(planoDeSaudeAntigo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PlanoDeSaude> update(@PathVariable("id") long id) {

        var planoDeSaudeAntigo = service.findById(id);
        if (planoDeSaudeAntigo == null) {
            return ResponseEntity.notFound().build();
        }

        service.delete(id);

        return new ResponseEntity<PlanoDeSaude>(planoDeSaudeAntigo, HttpStatus.OK);
    }
}
