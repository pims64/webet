
package com.webet.controllers;

import java.util.Date;
import java.util.List;

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

import com.webet.dao.IRencontreJpaRepository;
import com.webet.dao.ISportJpaRepository;
import com.webet.entities.Equipe;
import com.webet.entities.Rencontre;
import com.webet.entities.Sport;

@Secured("ROLE_ADMIN")
@Controller
@RequestMapping("/rencontrecontrolleur")
public class RencontreControlleur {

    @Autowired
    private IRencontreJpaRepository rencontreRepo;

    @Autowired
    private ISportJpaRepository sportsRepo;

    @GetMapping("/goToCreer")
    public String goToCreer(@ModelAttribute(value = "rencontre") Equipe equipe, Model model) {
	model.addAttribute("isGoToCreer", true);
	return "creerrencontre";
    }

    @PostMapping("/creer")
    public String creer(@ModelAttribute(value = "rencontre") Rencontre rencontre, BindingResult result, Model model) {
	if (!result.hasErrors()) {
	    if (rencontre.getEquipeDomicile().getId() != rencontre.getEquipeVisiteur().getId()) {
		Date dateDebut = rencontre.getDateDebut();
		Date dateFin = rencontre.getDateFin();
		if (dateDebut != null && dateFin != null) {
		    Date dateActuelle = new Date();
		    if (dateDebut.after(dateActuelle) && dateFin.after(dateDebut)) {
			rencontreRepo.save(rencontre);
		    }
		}
	    }
	}

	model.addAttribute("rencontre", new Rencontre());
	return "redirect:/admincontrolleur/goToAdmin";
    }

    @GetMapping("/goToModifier/{id}")
    public String goToModifier(@PathVariable("id") Long id, Model model) {
	Rencontre rencontre = rencontreRepo.getOne(id);
	model.addAttribute("rencontre", rencontre);
	return "modifierrencontre";
    }

    @GetMapping("/afficherliste")
    public String afficherListe(Model model) {
	List<Rencontre> rencontres = rencontreRepo.findAll();
	model.addAttribute("rencontres", rencontres);
	return "listerencontre";
    }

    @GetMapping("/afficherlisteAVenir")
    public String afficherListeAVenir(Model model) {
	Date dateCourante = new Date();
	List<Rencontre> rencontres = rencontreRepo.chercheRencontresAVenir(dateCourante);
	model.addAttribute("rencontres", rencontres);
	return "listerencontreavenir";
    }

    @SuppressWarnings("unused")
    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable("id") Long id, Model model) {
	rencontreRepo.deleteById(id);
	return "redirect:/admincontrolleur/goToAdmin";
    }

    @PostMapping("/goToDetail")
    public String goToDetail(@ModelAttribute(value = "sport") Sport sport, Model model) {
	model.addAttribute("equipes", sportsRepo.findAll());
	model.addAttribute("rencontres", rencontreRepo.findAll());
	return "rencontredetail";

    }
}
