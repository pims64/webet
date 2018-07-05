package com.webet.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @ManyToOne
    private Sport sport;

    public Equipe() {
    }

    @Override
    public String toString() {
	return "Equipe [id=" + id + ", nom=" + nom + ", sport=" + sport + "]";
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

    public void setSport(Sport sport) {
	this.sport = sport;
    }

}
