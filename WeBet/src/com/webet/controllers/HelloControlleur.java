package com.webet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webet.entities.Client;
import com.webet.entities.ERole;

@Controller
@RequestMapping("/hellocontrolleur")
public class HelloControlleur {

    @GetMapping("/goToSite")
    public String goToCreation(@ModelAttribute(value = "client") Client client, Model model) {
	if (AuthHelper.isAuthenticated()) {
	    if (AuthHelper.getRole().equals(ERole.ROLE_ADMIN))
		return "redirect:/admincontrolleur/goToAdmin";
	}

	return "redirect:/rencontrecontrolleur/pariEnCours";
    }

}
