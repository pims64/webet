
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
    public String creer(@Valid @ModelAttribute(value = "rencontre") Rencontre rencontre, BindingResult result,
	    Model model) {
	if (!result.hasErrors()) {
	    if (rencontre.getEquipeDomicile().getId() != rencontre.getEquipeVisiteur().getId()) {
		Date dateDebut = rencontre.getDateDebut();
		Date dateFin = rencontre.getDateFin();
		if (dateDebut != null && dateFin != null) {
		    Date dateActuelle = new Date();
		    dateActuelle = DateUtils.setSeconds(dateActuelle, 0);
		    dateActuelle = DateUtils.setMilliseconds(dateActuelle, 0);
		    if (dateDebut.after(dateActuelle) && dateFin.after(dateDebut)) {
			rencontreRepo.save(rencontre);
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
	model.addAttribute("rencontre", new Rencontre());
	return "rencontreDetail";
    }

    @GetMapping("/modifier/{sportId}/{rencontreId}")
    public String modifier(@PathVariable("sportId") Long sportId, @PathVariable("rencontreId") Long id, Model model) {
	Rencontre rencontre = rencontreRepo.getOne(id);
	model.addAttribute("rencontre", rencontre);
	populateRencontreDetail(sportId, model);
	return "rencontreDetail";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/afficherliste")
    public String afficherListe(Model model) {
	List<Rencontre> rencontres = rencontreRepo.findAll();
	model.addAttribute("rencontres", rencontres);
	return "listerencontre";
    }

    @Secured("ROLE_USER")
    @GetMapping("/afficherlisteAVenir")
    public String afficherListeAVenir(Model model) {
	Date dateCourante = new Date();
	List<Rencontre> rencontres = rencontreRepo.chercheRencontresAVenir(dateCourante);
	model.addAttribute("rencontres", rencontres);
	return "accueil";
    }

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
	return "rencontreDetail";
    }

    @GetMapping("/resultat") // Validation des paris associés à une rencontre après publication des résultats
    public String resultat(@ModelAttribute(value = "rencontre") Rencontre rencontre, Model model) {
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
		switch (resultatRencontre) {
		case VICTOIRE_DOMICILE:
		    pari.setGain(rencontre.getCoteDomicile() * pari.getSommePariee());
		    break;
		case VICTOIRE_VISITEUR:
		    pari.setGain(rencontre.getCoteVisiteur() * pari.getSommePariee());
		    break;
		case NUL:
		    pari.setGain(rencontre.getCoteNul() * pari.getSommePariee());
		    break;
		default:
		    break;
		}
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

    private void populateRencontreDetail(Long sportId, Model model) {
	model.addAttribute("equipes", equipeRepo.findBySportId(sportId));
	model.addAttribute("rencontres", rencontreRepo.findByEquipeDomicileSportId(sportId));
	model.addAttribute("sportId", sportId);
    }
}
