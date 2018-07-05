package com.webet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webet.entities.Client;

@Controller
@RequestMapping("/hellocontrolleur")
public class HelloControlleur {

    @GetMapping("/goToSite")
    public String goToCreation(@ModelAttribute(value = "client") Client client, Model model) {
	model.addAttribute("isGoToSite", true);
	if (AuthHelper.getRole().getName().equals("ROLE_ADMIN")) {
	    return "adminisration";
	}
	return "accueil";
    }

}