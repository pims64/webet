
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
import com.webet.entities.Pari;
import com.webet.entities.Rencontre;
import com.webet.entities.Sport;

@Secured("ROLE_ADMIN")
@Controller
@RequestMapping("/rencontrecontrolleur")
public class RencontreControlleur {

    @Autowired
    private IRencontreJpaRepository rencontreRepo;

    @Autowired
    private IPariJpaRepository pariRepo;

    @Autowired
    private IEquipeJpaRepository equipeRepo;

    @GetMapping("/goToCreer")
    public String goToCreer(@ModelAttribute(value = "rencontre") Rencontre rencontre, Model model) {
	model.addAttribute("isGoToCreer", true);
	return "creerrencontre";
    }

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
	    }
	}
	Long sportId = rencontre.getEquipeDomicile().getSport().getId();
	model.addAttribute("equipes", equipeRepo.findBySportId(sportId));
	model.addAttribute("rencontres", rencontreRepo.findByEquipeDomicileSportId(sportId));
	model.addAttribute("sportId", sportId);
	model.addAttribute("rencontre", new Rencontre());
	return "rencontreDetail";
    }

    @GetMapping("/goToModifier/{id}")
    public String goToModifier(@PathVariable("id") Long id, Model model) {
	Rencontre rencontre = rencontreRepo.getOne(id);
	model.addAttribute("rencontre", rencontre);
	return "rencontreDetail";
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

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable("id") Long id, Model model) {
	rencontreRepo.deleteById(id);
	return "redirect:/admincontrolleur/goToAdmin";
    }

    @PostMapping("/goToDetail")
    public String goToDetail(@ModelAttribute(value = "sport") Sport sport, Model model) {
	model.addAttribute("equipes", equipeRepo.findBySportId(sport.getId()));
	model.addAttribute("rencontres", rencontreRepo.findByEquipeDomicileSportId(sport.getId()));
	model.addAttribute("sportId", sport.getId());
	model.addAttribute("rencontre", new Rencontre());
	return "rencontreDetail";
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
