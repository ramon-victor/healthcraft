package br.univille.dacs2022.controller;

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
import br.univille.dacs2022.dto.MedicoDTO;
import br.univille.dacs2022.service.MedicoService;



@Controller
@RequestMapping("/medico")
public class MedicoController {
    
    @Autowired
    private MedicoService service;

    @GetMapping
    public ModelAndView index() {

        List<MedicoDTO> listaMedicos = service.getAll();

        return new ModelAndView("medico/index",
                "listaMedicos", listaMedicos);
    }

    @GetMapping("/novo")
    public ModelAndView novo() {
        var medico = new MedicoDTO();
        return new ModelAndView("medico/form",
                "medico", medico);
    }

    @PostMapping(params = "form")
    public ModelAndView save(@Valid @ModelAttribute("medico") MedicoDTO medico,
                                 BindingResult bindingResult) {
        
        if(bindingResult.hasErrors()){
            return new ModelAndView("medico/form");
        }
        service.save(medico);
        return new ModelAndView("redirect:/medico");

    }

    @GetMapping(path = "/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") long id) {
        MedicoDTO medico = service.findById(id);
        return new ModelAndView("medico/form","medico",medico);
    }

    @GetMapping(path = "/deletar/{id}")
    public ModelAndView deletar(@PathVariable("id") long id) {
        service.delete(id);
        return new ModelAndView("redirect:/medico");
    }
}
