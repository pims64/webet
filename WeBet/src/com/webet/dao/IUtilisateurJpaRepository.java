package com.webet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.webet.entities.Utilisateur;

public interface IUtilisateurJpaRepository extends JpaRepository<Utilisateur, Long> {

    @Query("select u from Utilisateur u where u.email = :email and u.motDePasse = :motDePasse")
    public Utilisateur findbyCredentials(@Param("email") String code, @Param("motDePasse") String motDePasse);

    @Query("select u from Utilisateur u where u.email = :email")
    public Utilisateur findByEmail(String email);
}
