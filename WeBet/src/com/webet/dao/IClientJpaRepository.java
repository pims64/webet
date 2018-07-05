package com.webet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webet.entities.Client;

public interface IClientJpaRepository extends JpaRepository<Client, Long> {

    // @Query("select c from Client c where c.utilisateur.email = :email")
    // public Client findByEmail(@Param("email") String email);

    public Client findByUtilisateurEmail(String email);

}
