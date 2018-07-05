package com.webet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webet.entities.Pari;

public interface IPariJpaRepository extends JpaRepository<Pari, Long> {

}
