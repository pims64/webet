package com.webet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webet.dao.IRencontreJpaRepository;

@Controller
@RequestMapping("/rencontrecontrolleur")
public class RencontreControlleur {

    @Autowired
    private IRencontreJpaRepository rencontreRepo;

}
