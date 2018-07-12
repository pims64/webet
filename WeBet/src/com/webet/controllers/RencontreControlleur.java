
package com.webet.controllers;

import java.util.Date;
import java.util.List;

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

import com.webet.dao.IEquipeJpaRepository;
import com.webet.dao.IPariJpaRepository;
import com.webet.dao.IRencontreJpaRepository;
import com.webet.entities.EChoixPari;
import com.webet.entities.Pari;
import com.webet.entities.Rencontre;
import com.webet.entities.Sport;

@Controller
@RequestMapping("/rencontrecontrolleur")
public class RencontreControlleur {

    @Autowired
    private IRencontreJpaRepository rencontreRepo;

    @Autowired
    private IPariJpaRepository pariRepo;

    @Autowired
    private IEquipeJpaRepository equipeRepo;

    @Secured("ROLE_ADMIN")
    @GetMapping("/goToCreer")
    public String goToCreer(@ModelAttribute(value = "rencontre") Rencontre rencontre, Model model) {
	model.addAttribute("isGoToCreer", true);
	return "creerrencontre";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/creer")
    public String creer(@RequestParam("isResultNeeded") boolean isResultNeeded,
	    @Valid @ModelAttribute(value = "rencontre") Rencontre rencontre, BindingResult result, Model model) {

	if (!result.hasErrors()) {
	    if (rencontre.getEquipeDomicile().getId() != rencontre.getEquipeVisiteur().getId()) {
		Date dateDebut = rencontre.getDateDebut();
		Date dateFin = rencontre.getDateFin();
		if (dateDebut != null && dateFin != null) {
		    Date dateActuelle = new Date();
		    dateActuelle = DateUtils.setSeconds(dateActuelle, 0);
		    dateActuelle = DateUtils.setMilliseconds(dateActuelle, 0);
		    if ((dateDebut.after(dateActuelle) && dateFin.after(dateDebut)) || isResultNeeded) {
			if (isResultNeeded) {
			    pariRepo.saveAll(resultat(rencontre));
			} else {
			    rencontreRepo.save(rencontre);
			}
		    } else {
			result.rejectValue("dateDebut", "error.rencontre.dateDebut.incorrecte");
		    }
		}
	    } else {
		result.rejectValue("equipeVisiteur", "error.rencontre.equipeVisiteur.identique");
	    }
	}

	Long sportId = rencontre.getEquipeDomicile().getSport().getId();

	populateRencontreDetail(sportId, model);
	model.addAttribute("sportId", sportId);
	model.addAttribute("isResultNeeded", false);
	model.addAttribute("rencontre", new Rencontre());
	return "rencontreDetail";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/modifier/{sportId}/{rencontreId}")
    public String modifier(@PathVariable("sportId") Long sportId, @PathVariable("rencontreId") Long id, Model model) {
	// Récupération de la rencontre
	Rencontre rencontre = rencontreRepo.getOne(id);

	// Formatage de la date actuelle
	Date dateActuelle = new Date();
	dateActuelle = DateUtils.setSeconds(dateActuelle, 0);
	dateActuelle = DateUtils.setMilliseconds(dateActuelle, 0);

	// Affectation du booléen si le résultat est attendu ou non
	boolean isResultNeeded = dateActuelle.after(rencontre.getDateFin());

	model.addAttribute("isResultNeeded", isResultNeeded);
	model.addAttribute("rencontre", rencontre);
	populateRencontreDetail(sportId, model);
	return "rencontreDetail";
    }

    @GetMapping("/pariEnCours")
    public String afficherListeAVenir(Model model) {
	Date dateCourante = new Date();
	dateCourante = DateUtils.setSeconds(dateCourante, 0);
	dateCourante = DateUtils.setMilliseconds(dateCourante, 0);

	List<Rencontre> rencontres = rencontreRepo.findByDateDebutGreaterThan(dateCourante);
	model.addAttribute("rencontres", rencontres);
	return "accueil";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/supprimer/{sportId}/{id}")
    public String supprimer(@PathVariable("sportId") Long sportId, @PathVariable("id") Long id, Model model) {
	rencontreRepo.deleteById(id);
	populateRencontreDetail(sportId, model);
	model.addAttribute("rencontre", new Rencontre());
	return "rencontreDetail";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/goToDetail")
    public String goToDetail(@ModelAttribute(value = "sport") Sport sport, Model model) {
	Long sportId = sport.getId();
	populateRencontreDetail(sportId, model);
	model.addAttribute("rencontre", new Rencontre());
	model.addAttribute("isResultNeeded", false);
	return "rencontreDetail";
    }

    private List<Pari> resultat(Rencontre rencontre) {
	EChoixPari resultatRencontre;
	if (rencontre.getScoreDomicile() - rencontre.getScoreVisiteur() > 0) {
	    resultatRencontre = EChoixPari.VICTOIRE_DOMICILE;
	} else if (rencontre.getScoreDomicile() - rencontre.getScoreVisiteur() < 0) {
	    resultatRencontre = EChoixPari.VICTOIRE_VISITEUR;
	} else {
	    resultatRencontre = EChoixPari.NUL;
	}
	List<Pari> listeParis = pariRepo.findByRencontreId(rencontre.getId());
	for (Pari pari : listeParis) {
	    if (resultatRencontre.equals(pari.getChoixPari())) {
		Double gain = resultatRencontre.calculGains(rencontre, pari);
		pari.setGain(gain);
	    }
	}

	return listeParis;

    }

    private void populateRencontreDetail(Long sportId, Model model) {
	model.addAttribute("equipes", equipeRepo.findBySportId(sportId));
	model.addAttribute("rencontres", rencontreRepo.findByEquipeDomicileSportIdOrderByDateFinDesc(sportId));
	model.addAttribute("sportId", sportId);
    }
}
