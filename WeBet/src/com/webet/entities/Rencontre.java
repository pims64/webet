package com.webet.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Rencontre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Equipe equipeVisiteur;

    @ManyToOne
    private Equipe equipeDomicile;

    private Date dateDebut;

    private Date dateFin;

    private Double coteVisiteur;

    private Double coteDomicile;

    private Double coteNul;

    private Integer scoreVisiteur;

    private Integer scoreDomicile;

    public Rencontre() {
    }

    @Override
    public String toString() {
	return "Rencontre [id=" + id + ", equipeVisiteur=" + equipeVisiteur + ", equipeDomicile=" + equipeDomicile
		+ ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", coteVisiteur=" + coteVisiteur
		+ ", coteDomicile=" + coteDomicile + ", coteNul=" + coteNul + ", scoreVisiteur=" + scoreVisiteur
		+ ", scoreDomicile=" + scoreDomicile + "]";
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Equipe getEquipeVisiteur() {
	return equipeVisiteur;
    }

    public void setEquipeVisiteur(Equipe equipeVisiteur) {
	this.equipeVisiteur = equipeVisiteur;
    }

    public Equipe getEquipeDomicile() {
	return equipeDomicile;
    }

    public void setEquipeDomicile(Equipe equipeDomicile) {
	this.equipeDomicile = equipeDomicile;
    }

    public Date getDateDebut() {
	return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
	this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
	return dateFin;
    }

    public void setDateFin(Date dateFin) {
	this.dateFin = dateFin;
    }

    public Double getCoteVisiteur() {
	return coteVisiteur;
    }

    public void setCoteVisiteur(Double coteVisiteur) {
	this.coteVisiteur = coteVisiteur;
    }

    public Double getCoteDomicile() {
	return coteDomicile;
    }

    public void setCoteDomicile(Double coteDomicile) {
	this.coteDomicile = coteDomicile;
    }

    public Double getCoteNul() {
	return coteNul;
    }

    public void setCoteNul(Double coteNul) {
	this.coteNul = coteNul;
    }

    public Integer getScoreVisiteur() {
	return scoreVisiteur;
    }

    public void setScoreVisiteur(Integer scoreVisiteur) {
	this.scoreVisiteur = scoreVisiteur;
    }

    public Integer getScoreDomicile() {
	return scoreDomicile;
    }

    public void setScoreDomicile(Integer scoreDomicile) {
	this.scoreDomicile = scoreDomicile;
    }

}
