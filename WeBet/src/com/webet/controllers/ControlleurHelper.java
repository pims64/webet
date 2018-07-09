package com.webet.controllers;

import org.springframework.ui.Model;

import com.webet.dao.IClientJpaRepository;
import com.webet.dao.IEquipeJpaRepository;
import com.webet.dao.ISportJpaRepository;
import com.webet.entities.Sport;

class ControlleurHelper {

    static void populateAdmin(Model model, IClientJpaRepository clientRepo, IEquipeJpaRepository equipeRepo,
	    ISportJpaRepository sportRepo) {

	model.addAttribute("sport", new Sport());
	model.addAttribute("clients", clientRepo.findAll());
	model.addAttribute("equipes", equipeRepo.findAll());
	model.addAttribute("sports", sportRepo.findAll());
    }

}
