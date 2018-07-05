package com.webet.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webet.dao.IClientJpaRepository;
import com.webet.dao.ISportJpaRepository;
import com.webet.entities.Client;
import com.webet.entities.Sport;

@Controller
@RequestMapping("/clientcontrolleur")
public class ClientControlleur {

    @Autowired
    private IClientJpaRepository clientRepo;

    @Autowired
    private ISportJpaRepository sportRepo;

    @GetMapping("/goToCreer")
    public String goToCreation(@ModelAttribute(value = "client") Client client, Model model) {
	model.addAttribute("isGoToCreer", true);
	return "inscription";
    }

    @PostMapping("/creer")
    public String creer(@ModelAttribute(value = "client") Client client, BindingResult result, Model model) {
	clientRepo.save(client);
	model.addAttribute("client", new Client());
	List<Sport> sports = sportRepo.findAll();
	model.addAttribute("sports", sports);
	return "accueil";

    }
}
