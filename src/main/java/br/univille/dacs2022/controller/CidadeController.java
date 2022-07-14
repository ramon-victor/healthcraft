package br.univille.dacs2022.controller;

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

import br.univille.dacs2022.dto.CidadeDTO;
import br.univille.dacs2022.service.CidadeService;

@Controller
@RequestMapping("/cidade")
public class CidadeController {
    @Autowired
    private CidadeService service;

    @GetMapping("/novo")
    public ModelAndView novo() {
        var cidade = new CidadeDTO();
        return new ModelAndView("cidade/form",
                "cidade", cidade);
    }
    
    @PostMapping(params = "save")
    public ModelAndView save(@Valid @ModelAttribute("cidade") CidadeDTO cidade,
                                BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return new ModelAndView("cidade/form");
        }
        service.save(cidade);
        return new ModelAndView("redirect:/paciente/novo");

    }

    @GetMapping(path = "/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") long id) {
        CidadeDTO cidade = service.findById(id);
        return new ModelAndView("cidade/form","cidade",cidade);
    }

    @GetMapping(path = "/deletar/{id}")
    public ModelAndView deletar(@PathVariable("id") long id) {
        service.delete(id);
        return new ModelAndView("redirect:/cidade");
    }
}
