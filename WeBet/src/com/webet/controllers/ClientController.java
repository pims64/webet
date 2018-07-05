package com.webet.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webet.dao.IClientJpaRepository;
import com.webet.dao.ISportJpaRepository;
import com.webet.entities.Client;
import com.webet.entities.Sport;

@Controller
@RequestMapping("/clientcontroller")
public class ClientController {

    @Autowired
    private IClientJpaRepository clientRepo;

    @Autowired
    private ISportJpaRepository sportRepo;

    @PostMapping("/creer")
    public String creer(@ModelAttribute(value = "client") Client client, BindingResult result, Model model) {
	// if (!result.hasErrors()) {
	// encodePassword(client);
	clientRepo.save(client);
	model.addAttribute("client", new Client());
	// }
	List<Sport> sports = sportRepo.findAll();
	model.addAttribute("sports", sports);
	return "accueil";

    }

}
