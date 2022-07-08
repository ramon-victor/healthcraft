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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import br.univille.dacs2022.dto.PacienteDTO;
import br.univille.dacs2022.service.CidadeService;
import br.univille.dacs2022.service.PacienteService;
import br.univille.dacs2022.service.PlanoDeSaudeService;

@Controller
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService service;
    @Autowired
    private CidadeService cidadeService;
    @Autowired
    private PlanoDeSaudeService planoDeSaudeService;
    
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
        var listaPlanos = planoDeSaudeService.getAll();
        HashMap<String,Object> dados = new HashMap<>();
        dados.put("paciente", paciente);
        dados.put("listaCidades", listaCidades);
        dados.put("listaPlanos", listaPlanos);
        return new ModelAndView("paciente/form", dados);
    }

    @PostMapping(params = "save")
    public ModelAndView save(@Valid @ModelAttribute("paciente") PacienteDTO paciente,
            BindingResult bindingResult) {

        var cidadeDTO = cidadeService.findById(paciente.getCidadeId());
        paciente.setCidade(cidadeDTO);

        if (bindingResult.hasErrors()) {
            var listaCidades = cidadeService.getAll();
            HashMap<String,Object> dados = new HashMap<>();
            dados.put("listaCidades",listaCidades);
            return new ModelAndView("paciente/form", dados);
        }

        service.save(paciente);
        return new ModelAndView("redirect:/paciente");

    }

    @PostMapping(params="incplano")
    public ModelAndView incluirPlano(@Valid @ModelAttribute("paciente") 
                                PacienteDTO paciente,
                                BindingResult bindingResult){
        var idPlanoSelect = paciente.getPlanoId();
        var planoSelect = planoDeSaudeService.getById(idPlanoSelect);
        paciente.getListaPlanos().add(planoSelect);

        var listaCidades = cidadeService.getAll();
        var listaPlanos = planoDeSaudeService.getAll();
        HashMap<String,Object> dados = new HashMap<>();
        dados.put("paciente",paciente);
        dados.put("listaCidades",listaCidades);
        dados.put("listaPlanos",listaPlanos);

        return new ModelAndView("paciente/form",dados);
    }
    
    @PostMapping(params="removeitem")
    public ModelAndView removerPlano(@Valid @ModelAttribute("paciente") 
                                PacienteDTO paciente,
                                @RequestParam(name = "removeitem") int index,
                                BindingResult bindingResult){
        paciente.getListaPlanos().remove(index);

        var listaCidades = cidadeService.getAll();
        var listaPlanos = planoDeSaudeService.getAll();
        HashMap<String,Object> dados = new HashMap<>();
        dados.put("paciente",paciente);
        dados.put("listaCidades",listaCidades);
        dados.put("listaPlanos",listaPlanos);

        return new ModelAndView("paciente/form",dados);
    }

    @GetMapping(path = "/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") long id) {
        PacienteDTO paciente = service.findById(id);
        var listaCidades = cidadeService.getAll();
        var listaPlanos = planoDeSaudeService.getAll();
        HashMap<String,Object> dados = new HashMap<>();
        dados.put("paciente", paciente);
        dados.put("listaCidades", listaCidades);
        dados.put("listaPlanos",listaPlanos);
        return new ModelAndView("paciente/form", dados);
    }

    @GetMapping(path = "/deletar/{id}")
    public ModelAndView deletar(@PathVariable("id") long id) {
        service.delete(id);
        return new ModelAndView("redirect:/paciente");
    }
}
