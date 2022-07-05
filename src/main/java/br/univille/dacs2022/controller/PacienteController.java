package br.univille.dacs2022.controller;

import java.util.HashMap;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import br.univille.dacs2022.dto.PacienteDTO;
import br.univille.dacs2022.service.CidadeService;
import br.univille.dacs2022.service.PacienteService;

@Controller
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService service;
    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public ModelAndView index() {

        List<PacienteDTO> listaPacientes = service.getAll();

        return new ModelAndView("paciente/index",
                "listaPacientes", listaPacientes);
    }

    @GetMapping("/novo")
    public ModelAndView novo() {
        var paciente = new PacienteDTO();
        var listaCidades = cidadeService.getAll();
        HashMap<String,Object> dados = new HashMap<>();
        dados.put("paciente", paciente);
        dados.put("listaCidades", listaCidades);
        return new ModelAndView("paciente/form", dados);
    }

    @PostMapping(params = "form")
    public ModelAndView save(@Valid @ModelAttribute("paciente") PacienteDTO paciente,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("paciente/form");
        }
        service.save(paciente);
        return new ModelAndView("redirect:/paciente");

    }

    @GetMapping(path = "/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") long id) {
        PacienteDTO paciente = service.findById(id);
        return new ModelAndView("paciente/form", "paciente", paciente);
    }

    @GetMapping(path = "/deletar/{id}")
    public ModelAndView deletar(@PathVariable("id") long id) {
        service.delete(id);
        return new ModelAndView("redirect:/paciente");
    }
}
