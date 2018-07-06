package com.webet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webet.entities.Rencontre;

public interface IRencontreJpaRepository extends JpaRepository<Rencontre, Long> {

    // @Query("select r from Rencontre r where r.email = :email and u.motDePasse =
    // :motDePasse")
    // public rencontre findbyDateAVenir(@Param("email") String code,
    // @Param("motDePasse") String motDePasse);

}
