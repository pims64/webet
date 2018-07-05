package com.webet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webet.entities.Sport;

public interface ISportJpaRepository extends JpaRepository<Sport, Long> {

}
