package com.webet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webet.dao.IPariJpaRepository;

@Controller
@RequestMapping("/rencontrecontrolleur")
public class PariControlleur {

    @Autowired
    private IPariJpaRepository pariRepo;

}
