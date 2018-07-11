package com.webet.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.webet.entities.Rencontre;

public interface IRencontreJpaRepository extends JpaRepository<Rencontre, Long> {

    @Query("select r from Rencontre r where r.dateDebut > :dateCourante")
    public List<Rencontre> chercheRencontresAVenir(@Param("dateCourante") Date dateCourante);

    public List<Rencontre> findByDateDebutLessThan(Date dateCourante);

    public List<Rencontre> findByDateDebutGreaterThan(Date dateCourante);

    public List<Rencontre> findByEquipeVisiteurSportId(Long idSport);

    @Query("select r from Rencontre r where r.dateFin < :dateCourante and (r.scoreVisiteur = null or r.scoreDomicile = null)")
    public List<Rencontre> chercheRencontresFiniesSansScore(@Param("dateCourante") Date dateCourante);

    public List<Rencontre> findByEquipeDomicileSportIdOrderByDateDebutAsc(Long id);

}
