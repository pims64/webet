package com.webet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webet.entities.Pari;

public interface IPariJpaRepository extends JpaRepository<Pari, Long> {

    public List<Pari> findByRencontreId(Long idRencontre);

    public List<Pari> findByClientIdOrderByDateCreationDesc(Long idClient);

}
