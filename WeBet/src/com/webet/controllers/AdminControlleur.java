package com.webet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webet.dao.IClientJpaRepository;
import com.webet.dao.IEquipeJpaRepository;
import com.webet.dao.ISportJpaRepository;
import com.webet.entities.Equipe;

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

    @GetMapping("/goToAdmin")
    public String goToAdmin(Model model) {
	model.addAttribute("equipe", new Equipe());
	model.addAttribute("sports", sportRepo.findAll());
	model.addAttribute("equipes", equipeRepo.findAll());
	model.addAttribute("clients", clientRepo.findAll());
	return "administration";
    }
}
