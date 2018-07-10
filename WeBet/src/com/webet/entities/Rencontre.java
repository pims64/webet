package com.webet.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Rencontre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{error.rencontre.equipeVisiteur.obligatoire}")
    @ManyToOne
    private Equipe equipeVisiteur;

    @NotNull(message = "{error.rencontre.equipeDomicile.obligatoire}")
    @ManyToOne
    private Equipe equipeDomicile;

    @NotNull(message = "{error.rencontre.dateDebut.obligatoire}")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dateDebut;

    @NotNull(message = "{error.rencontre.dateFin.obligatoire}")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dateFin;

    @NotNull(message = "{error.rencontre.coteVisiteur.obligatoire}")
    @Min(value = 1, message = "{error.rencontre.coteVisiteur.superieur1}")
    private Double coteVisiteur;

    @NotNull(message = "{error.rencontre.coteDomicile.obligatoire}")
    @Min(value = 1, message = "{error.rencontre.coteDomicile.superieur1}")
    private Double coteDomicile;

    @NotNull(message = "{error.rencontre.coteNul.obligatoire}")
    @Min(value = 1, message = "{error.rencontre.coteNul.superieur1}")
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
