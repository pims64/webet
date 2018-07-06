package com.webet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.webet.entities.Rencontre;

public interface IRencontreJpaRepository extends JpaRepository<Rencontre, Long> {

    @Query("select r from Rencontre r where r.dateFin > :dateCourante")
    public List<Rencontre> findbyDateAVenir(@Param("dateCourante") String dateCourante);

}
