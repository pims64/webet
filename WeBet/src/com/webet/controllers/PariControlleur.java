package com.webet.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webet.dao.IPariJpaRepository;
import com.webet.entities.Pari;

@Controller
@RequestMapping("/paricontrolleur")
@Secured("ROLE_ADMIN")
public class PariControlleur {

    @Autowired
    private IPariJpaRepository pariRepo;

    @GetMapping("/goToCreer")
    public String goToCreer(@ModelAttribute(value = "pari") Pari pari, Model model) {
	model.addAttribute("isGoToCreer", true);
	return "administration";
    }

    @PostMapping("/creer")
    public String creer(@Valid @ModelAttribute(value = "pari") Pari pari, BindingResult result, Model model) {
	if (!result.hasErrors()) {
	    pariRepo.save(pari);
	    model.addAttribute("pari", new Pari());
	    return "administration";
	}
	return "pari";

    }

    @GetMapping("/lister")
    public String lister(Model model) {
	model.addAttribute("paris", pariRepo.findAll());
	return "administration";
    }

    @GetMapping("/goToModifier")
    public String goToModifier(@ModelAttribute(value = "pari") Pari pari, Model model) {
	model.addAttribute("isGoToModifier", true);
	return "administration";
    }

    @GetMapping("/modifier/{id}")
    public String modifier(@PathVariable(value = "id", required = false) Long id, Model model) {
	model.addAttribute("pari", pariRepo.getOne(id));
	return "redirect:/paricontrolleur/creer";
    }

    @GetMapping("/supprimer/{id}")
    public String delete(@PathVariable(value = "id", required = true) Long id, Model model) {
	pariRepo.deleteById(id);
	return "administration";
    }
}
