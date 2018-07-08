package com.webet.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webet.dao.IClientJpaRepository;
import com.webet.dao.IEquipeJpaRepository;
import com.webet.dao.IRencontreJpaRepository;
import com.webet.dao.ISportJpaRepository;
import com.webet.entities.Equipe;
import com.webet.entities.Pari;
import com.webet.entities.Rencontre;
import com.webet.entities.Sport;

@Controller
@RequestMapping("/admincontrolleur")
@Secured("ROLE_ADMIN")
public class AdminControlleur {

    @Autowired
    private ISportJpaRepository sportRepo;

    @Autowired
    private IEquipeJpaRepository equipeRepo;

    @Autowired
    private IClientJpaRepository clientRepo;

    @Autowired
    private IRencontreJpaRepository rencontreRepo;

    @GetMapping("/goToAdmin")
    public String goToAdmin(Model model) {

	// Envoi d'objets vides pour création dans la page d'admin
	model.addAttribute("equipe", new Equipe());
	model.addAttribute("rencontre", new Rencontre());
	model.addAttribute("pari", new Pari());

	// Génération des listes pour affichage dans la page d'admin
	List<Sport> sports = sportRepo.findAll();
	model.addAttribute("sports", sports);
	model.addAttribute("equipes", equipeRepo.findAll());
	model.addAttribute("clients", clientRepo.findAll());
	model.addAttribute("rencontres", rencontreRepo.findAll());

	// Génération des listes par sport pour créer des rencontres
	Map<Sport, List<Equipe>> equipesSport = new HashMap<>();
	for (Sport sport : sports) {
	    equipesSport.put(sport, equipesParSport(sport));
	}
	model.addAttribute("equipesSport", equipesSport);

	// Renvoi sur la page générale d'administration
	return "administration";
    }

    private List<Equipe> equipesParSport(Sport sport) {
	List<Equipe> equipes = equipeRepo.findBySportId(sport.getId());
	return equipes;
    }
}
