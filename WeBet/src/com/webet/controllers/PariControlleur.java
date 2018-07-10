package com.webet.controllers;

import java.util.Date;

import javax.validation.Valid;

import org.apache.commons.lang3.time.DateUtils;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.webet.dao.IPariJpaRepository;
import com.webet.dao.IRencontreJpaRepository;
import com.webet.entities.Client;
import com.webet.entities.EChoixPari;
import com.webet.entities.Pari;
import com.webet.entities.Rencontre;

@Controller
@RequestMapping("/paricontrolleur")
@Secured("ROLE_USER")
public class PariControlleur {

    @Autowired
    private IPariJpaRepository pariRepo;

    @Autowired
    private IRencontreJpaRepository rencontreRepo;

    @GetMapping("/goToCreer/{id}")
    public String goToCreer(@PathVariable(value = "id", required = true) Long id,
	    @ModelAttribute(value = "pari") Pari pari, Model model) {
	Rencontre rencontre = rencontreRepo.getOne(id);
	model.addAttribute("rencontre", rencontre);
	model.addAttribute("rencontreId", rencontre.getId());
	model.addAttribute("client", AuthHelper.getPrincipal().getClient());
	model.addAttribute("choixPari", EChoixPari.values());
	return "paris";
    }

    @PostMapping("/creer")

    public String creer(@Valid @ModelAttribute(value = "pari") Pari pari, BindingResult result,
	    @RequestParam("rencontreId") Long rencontreId, Model model) {

	Client client = AuthHelper.getPrincipal().getClient();
	Rencontre rencontre = rencontreRepo.getOne(rencontreId);

	Date dateCreation = new Date();
	dateCreation = DateUtils.setSeconds(dateCreation, 0);
	dateCreation = DateUtils.setMilliseconds(dateCreation, 0);

	pari.setDateCreation(dateCreation);
	pari.setClient(client);
	pari.setRencontre(rencontre);

	if (!result.hasErrors()) {
	    System.out.println("validation");
	    if (pari.getSommePariee() <= pari.getClient().getMontantMaxPari()) {
		System.out.println("condition montant");
		pariRepo.save(pari);
		model.addAttribute("pari", new Pari());
		return "paris";
	    } else {
		model.addAttribute("message",
			"La somme pariée est supérieure à votre plafond maximum autorisé, Réessayer");
		System.out.println("La somme pariée est supérieure à votre plafond maximum autorisé, Réessayer");
		return "paris";
	    }
	}
	System.out.println("retour site");
	return "redirect:/hellocontrolleur/goToSite";
    }

    @GetMapping("/lister")
    public String lister(Model model) {
	model.addAttribute("paris", pariRepo.findAll());
	return "administration";
    }

    @GetMapping("/goToModifier")
    public String goToModifier(@ModelAttribute(value = "pari") Pari pari, Model model) {
	model.addAttribute("isGoToModifier", true);
	return "administration";
    }

    @GetMapping("/modifier/{id}")
    public String modifier(@PathVariable(value = "id", required = false) Long id, Model model) {
	model.addAttribute("pari", pariRepo.getOne(id));
	return "redirect:/paricontrolleur/creer";
    }

    @GetMapping("/supprimer/{id}")
    public String delete(@PathVariable(value = "id", required = true) Long id, Model model) {
	pariRepo.deleteById(id);
	return "administration";
    }
}
