package com.webet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webet.entities.Equipe;

@Controller
@RequestMapping("/equipecontrolleur")
public class EquipeControlleur {

    @GetMapping("/goToCreer")
    public String goToCreation(@ModelAttribute(value = "equipe") Equipe equipe, Model model) {
	model.addAttribute("isGoToCreer", true);
	return "creerequipe";
    }

    // @PostMapping("/creer")
    // public String creer(@ModelAttribute(value = "client") Client client,
    // BindingResult result, Model model) {
    // if (!result.hasErrors()) {
    // encodePassword(client);
    // clientRepo.save(client);
    // model.addAttribute("client", new Client());
    // }
    // List<Sport> sports = sportRepo.findAll();
    // model.addAttribute("sports", sports);
    // return "inscription";
    //
    // }

}
