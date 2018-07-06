package com.webet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webet.dao.IRencontreJpaRepository;
import com.webet.entities.Equipe;
import com.webet.entities.Rencontre;

@Secured("ROLE_ADMIN")
@Controller
@RequestMapping("/rencontrecontrolleur")
public class RencontreControlleur {

    @Autowired
    private IRencontreJpaRepository rencontreRepo;

    @GetMapping("/goToCreer")
    public String goToCreation(@ModelAttribute(value = "rencontre") Equipe equipe, Model model) {
	model.addAttribute("isGoToCreer", true);
	return "creerrencontre";
    }

    @PostMapping("/creer")
    public String creer(@ModelAttribute(value = "rencontre") Rencontre rencontre, Model model) {
	rencontreRepo.save(rencontre);
	model.addAttribute("rencontre", new Rencontre());

	return "creerrencontre";

    }

    @GetMapping("/goToModifier/{id}")
    public String goToModifier(@PathVariable("id") Long id, Model model) {
	Rencontre rencontre = rencontreRepo.getOne(id);
	model.addAttribute("rencontre", rencontre);
	return "modifierrencontre";
    }

    @PostMapping("/modifier")
    public String modifier(@ModelAttribute(value = "rencontre") Rencontre rencontre, Model model) {
	rencontreRepo.save(rencontre);

	return "modifierr";
    }
}
