package com.webet.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Pari {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Rencontre rencontre;

    private Double sommePariee;

    private Integer choixPari;

    public Pari() {
    }

    @Override
    public String toString() {
	return "Pari [id=" + id + ", client=" + client + ", rencontre=" + rencontre + ", sommePariee=" + sommePariee
		+ ", choixPari=" + choixPari + "]";
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

    public Integer getChoixPari() {
	return choixPari;
    }

    public void setChoixPari(Integer choixPari) {
	this.choixPari = choixPari;
    }

}
