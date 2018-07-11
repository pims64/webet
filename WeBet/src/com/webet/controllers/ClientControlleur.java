package com.webet.controllers;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.time.DateUtils;
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
import com.webet.dao.IPariJpaRepository;
import com.webet.dao.IRencontreJpaRepository;
import com.webet.entities.Client;
import com.webet.entities.ERole;
import com.webet.entities.Pari;
import com.webet.entities.Rencontre;

@Controller
@RequestMapping("/clientcontrolleur")
public class ClientControlleur {

    @Autowired
    private IClientJpaRepository clientRepo;

    @Autowired
    private IRencontreJpaRepository rencontreRepo;

    @Autowired
    private IPariJpaRepository pariRepo;

    @GetMapping("/goToCreer")
    public String goToCreer(@ModelAttribute(value = "client") Client client, Model model) {
	return "inscription";
    }

    @PostMapping("/creer")
    public String creer(@Valid @ModelAttribute(value = "client") Client client, BindingResult result, Model model) {
	if (!result.hasErrors()) {

	    // Encodage du mot de passe
	    encodePassword(client);
	    client.getUtilisateur().setRole(ERole.ROLE_USER);
	    model.addAttribute("client", clientRepo.save(client));

	    populateListePari(model);

	    return "accueil";
	}
	return "inscription";
    }

    private static void encodePassword(Client client) {
	String rawPassword = client.getUtilisateur().getMotDePasse();
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	String encodedPassword = encoder.encode(rawPassword);
	client.getUtilisateur().setMotDePasse(encodedPassword);
    }

    @Secured("ROLE_USER")
    @GetMapping("/modifier/{id}")
    public String modifier(@PathVariable("id") Long id, Model model) {
	if (id != AuthHelper.getPrincipal().getClient().getId()) {
	    return "redirect:/hellocontrolleur/goToSite";
	}

	Long idClient = AuthHelper.getPrincipal().getClient().getId();

	// récupération du client
	Client client = clientRepo.getOne(idClient);
	model.addAttribute("client", client);

	// récupération des paris du client
	List<Pari> paris = pariRepo.findByClientIdOrderByDateCreationDesc(idClient);
	model.addAttribute("paris", paris);

	return "modification";
    }

    @PostMapping("/modifier")
    public String modifier(@Valid @ModelAttribute(value = "client") Client client, BindingResult result, Model model) {

	// récupération des paris du client
	List<Pari> paris = pariRepo.findByClientIdOrderByDateCreationDesc(client.getId());
	model.addAttribute("paris", paris);

	if (!result.hasErrors()) {
	    // Encodage du mot de passe
	    encodePassword(client);
	    model.addAttribute("client", clientRepo.save(client));

	    populateListePari(model);
	    return "accueil";
	}
	return "modification";
    }

    @Secured("ROLE_ADMIN")
    @SuppressWarnings("unused")
    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable("id") Long id, Model model) {
	clientRepo.deleteById(id);
	return "redirect:/admincontrolleur/goToAdmin";
    }

    private void populateListePari(Model model) {
	// On peuple la page d'accueil avant la redirection
	Date dateCourante = new Date();
	dateCourante = DateUtils.setSeconds(dateCourante, 0);
	dateCourante = DateUtils.setMilliseconds(dateCourante, 0);
	List<Rencontre> rencontres = rencontreRepo.findByDateDebutGreaterThan(dateCourante);
	model.addAttribute("rencontres", rencontres);
    }

}
