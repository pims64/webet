package com.webet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webet.entities.Equipe;

public interface IEquipeJpaRepository extends JpaRepository<Equipe, Long> {

    public List<Equipe> findBySportId(Long id);

}
