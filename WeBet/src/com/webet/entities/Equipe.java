package com.webet.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Entity
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "{error.equipe.nom.obligatoire}")
    private String nom;

    private String nomImg;

    @Valid
    @ManyToOne
    private Sport sport;

    public Equipe() {
    }

    @Override
    public String toString() {
	return "Equipe [id=" + id + ", nom=" + nom + ", nomImg=" + nomImg + ", sport=" + sport + "]";
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getNom() {
	return nom;
    }

    public void setNom(String nom) {
	this.nom = nom;
    }

    public Sport getSport() {
	return sport;
    }

    public String getNomImg() {
	return nomImg;
    }

    public void setNomImg(String nomImg) {
	this.nomImg = nomImg;
    }

    public void setSport(Sport sport) {
	this.sport = sport;
    }

}
