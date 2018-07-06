package com.webet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @GetMapping("/goToCreer")
    public String goToCreer(@ModelAttribute(value = "equipe") Equipe equipe, Model model) {
	model.addAttribute("isGoToCreer", true);
	return "creerequipe";
    }

    @PostMapping("/creer")
    public String creer(@ModelAttribute(value = "equipe") Equipe equipe, Model model) {
	equipeRepo.save(equipe);
	model.addAttribute("equipe", new Equipe());

	return "creerequipe";

    }

}
