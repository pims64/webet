package com.webet.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webet.dao.IClientJpaRepository;
import com.webet.dao.ISportJpaRepository;
import com.webet.entities.Client;
import com.webet.entities.ERole;
import com.webet.entities.Sport;

@Controller
@RequestMapping("/clientcontrolleur")
public class ClientControlleur {

    @Autowired
    private IClientJpaRepository clientRepo;

    @Autowired
    private ISportJpaRepository sportRepo;

    @GetMapping("/goToCreer")
    public String goToCreer(@ModelAttribute(value = "client") Client client, Model model) {
	return "inscription";
    }

    @PostMapping("/creer")
    public String creer(@Valid @ModelAttribute(value = "client") Client client, BindingResult result, Model model) {
	if (!result.hasErrors()) {
	    encodePassword(client);
	    client.getUtilisateur().setRole(ERole.ROLE_USER);
	    model.addAttribute("client", clientRepo.save(client));
	    return "accueil";
	}
	// List<Sport> sports = sportRepo.findAll();
	// model.addAttribute("sports", sports);
	return "accueil";
    }

    private static void encodePassword(Client client) {
	String rawPassword = client.getUtilisateur().getMotDePasse();
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	String encodedPassword = encoder.encode(rawPassword);
	client.getUtilisateur().setMotDePasse(encodedPassword);
    }

    @GetMapping("/goToModifier/{id}")
    public String goToModifier(@PathVariable("id") Long id, Model model) {
	Client client = clientRepo.getOne(id);
	model.addAttribute("client", client);
	List<Sport> sports = sportRepo.findAll();
	model.addAttribute("sports", sports);
	model.addAttribute("roles", ERole.values());
	return "modifierclient";
    }

    // @PostMapping("/modifier")
    // public String modifier(@Valid @ModelAttribute(value = "client") Client
    // client, BindingResult result, Model model) {
    // if (!result.hasErrors()) {
    // encodePassword(client);
    // clientRepo.save(client);
    // }
    // List<Sport> sports = sportRepo.findAll();
    // model.addAttribute("sports", sports);
    // return "modifierclient";
    // }

    @Secured("ROLE_ADMIN")
    @GetMapping("/afficherliste")
    public String afficherListe(Model model) {
	List<Client> clients = clientRepo.findAll();
	model.addAttribute("clients", clients);
	return "listeclient";
    }

    @Secured("ROLE_ADMIN")
    @SuppressWarnings("unused")
    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable("id") Long id, Model model) {
	clientRepo.deleteById(id);
	return "redirect:/clientcontrolleur/afficherliste";
    }

}
