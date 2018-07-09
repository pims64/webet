package com.webet.controllers;

import java.util.List;

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

import com.webet.dao.IEquipeJpaRepository;
import com.webet.entities.Equipe;

@Secured("ROLE_ADMIN")
@Controller
@RequestMapping("/equipecontrolleur")
public class EquipeControlleur {

    @Autowired
    private IEquipeJpaRepository equipeRepo;

    // @GetMapping("/goToCreer")
    // public String goToCreer(@ModelAttribute(value = "equipe") Equipe equipe,
    // Model model) {
    // model.addAttribute("isGoToCreer", true);
    // return "creerequipe";
    // }

    @PostMapping("/creer")
    public String creer(@Valid @ModelAttribute(value = "equipe") Equipe equipe, Model model, BindingResult result) {
	if (!result.hasErrors()) {
	    equipeRepo.save(equipe);
	    model.addAttribute("equipe", new Equipe());
	}

	return "redirect:/admincontrolleur/goToAdmin";

    }

    @GetMapping("/afficherliste")
    public String afficherListe(Model model) {
	List<Equipe> equipes = equipeRepo.findAll();
	model.addAttribute("equipes", equipes);
	return "listeequipe";
    }

    @GetMapping("/goToModifier/{id}")
    public String goToModifier(@PathVariable("id") Long id, Model model) {
	Equipe equipe = equipeRepo.getOne(id);
	model.addAttribute("equipe", equipe);
	return "modifierequipe";
    }

    @SuppressWarnings("unused")
    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable("id") Long id, Model model) {
	equipeRepo.deleteById(id);
	return "redirect:/admincontrolleur/goToAdmin";
    }

}
