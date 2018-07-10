package com.webet.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Pari {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Rencontre rencontre;

    @NotNull(message = "{error.pari.sommePariee.obligatoire}")
    private Double sommePariee;

    @NotNull(message = "{error.pari.choixPari.obligatoire}")
    @Enumerated(EnumType.STRING)
    private EChoixPari choixPari;

    private Double gain = 0.0;

    private Date dateCreation;

    public Pari() {
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Client getClient() {
	return client;
    }

    public void setClient(Client client) {
	this.client = client;
    }

    public Rencontre getRencontre() {
	return rencontre;
    }

    public void setRencontre(Rencontre rencontre) {
	this.rencontre = rencontre;
    }

    public Double getSommePariee() {
	return sommePariee;
    }

    public void setSommePariee(Double sommePariee) {
	this.sommePariee = sommePariee;
    }

    public EChoixPari getChoixPari() {
	return choixPari;
    }

    public void setChoixPari(EChoixPari choixPari) {
	this.choixPari = choixPari;
    }

    public Double getGain() {
	return gain;
    }

    public void setGain(Double gain) {
	this.gain = gain;
    }

    public Date getDateCreation() {
	return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
	this.dateCreation = dateCreation;
    }

    @Override
    public String toString() {
	return "Pari [id=" + id + ", client=" + client + ", rencontre=" + rencontre + ", sommePariee=" + sommePariee
		+ ", choixPari=" + choixPari + ", gain=" + gain + ", dateCreation=" + dateCreation + "]";
    }

}
