
package com.webet.controllers;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

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

import com.webet.dao.IPariJpaRepository;
import com.webet.dao.IRencontreJpaRepository;
import com.webet.entities.Pari;
import com.webet.entities.Rencontre;

@Secured("ROLE_ADMIN")
@Controller
@RequestMapping("/rencontrecontrolleur")
public class RencontreControlleur {

    @Autowired
    private IRencontreJpaRepository rencontreRepo;

    @Autowired
    private IPariJpaRepository pariRepo;

    @GetMapping("/goToCreer")
    public String goToCreer(@ModelAttribute(value = "rencontre") Rencontre rencontre, Model model) {
	model.addAttribute("isGoToCreer", true);
	return "creerrencontre";
    }

    @PostMapping("/creer")
    public String creer(@Valid @ModelAttribute(value = "rencontre") Rencontre rencontre, BindingResult result,
	    Model model) {

	if (!result.hasErrors()) {
	    rencontreRepo.save(rencontre);
	    model.addAttribute("rencontre", new Rencontre());
	}
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

    @GetMapping("/afficherListeAVenir")
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
	return "redirect:/rencontrecontrolleur/afficherListe";
    }

    @GetMapping("/resultat") // Validation des paris associés à une rencontre après publication des résultats
    public String resultat(@ModelAttribute(value = "rencontre") Rencontre rencontre, Model model) {
	String resultatRencontre;
	if (rencontre.getScoreDomicile() - rencontre.getScoreVisiteur() > 0)
	    resultatRencontre = "VICTOIRE_DOMICILE";
	else if (rencontre.getScoreDomicile() - rencontre.getScoreVisiteur() < 0)
	    resultatRencontre = "VICTOIRE_VISITEUR";
	else
	    resultatRencontre = "NUL";
	List<Pari> listeParis = pariRepo.findByRencontreId(rencontre.getId());
	for (Pari pari : listeParis) {
	    if (resultatRencontre.equals(pari.getChoixPari().getName())) {
		pari.setResultat(true);
	    } else {
		pari.setResultat(false);
	    }

	}

	// for (int i = 0; i < listeParis.size(); i++) {
	// if (resultatRencontre.equals(listeParis.get(i).getChoixPari().getName()))
	// listeParis.get(i).setResultat(true);
	// else
	// listeParis.get(i).setResultat(false);
	// }

	return "rencontreDetail";
    }
}
