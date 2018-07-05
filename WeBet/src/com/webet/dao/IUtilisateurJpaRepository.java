package com.webet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webet.entities.Utilisateur;

public interface IUtilisateurJpaRepository extends JpaRepository<Utilisateur, Long> {

}
