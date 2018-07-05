package com.webet.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String prenom;

    private Integer age;

    @Embedded
    private Adresse adresse;

    private String telephoneFixe;

    private String telephoneMobile;

    @OneToMany
    private List<Sport> listeSports;

    private Double montantMaxPari;

    @OneToOne(cascade = { CascadeType.ALL })
    private Utilisateur utilisateur;

    public Client() {

    }

    @Override
    public String toString() {
	return "Client [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", adresse=" + adresse
		+ ", telephoneFixe=" + telephoneFixe + ", telephoneMobile=" + telephoneMobile + ", listeSports="
		+ listeSports + ", montantMaxPari=" + montantMaxPari + ", utilisateur=" + utilisateur + "]";
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

    public String getPrenom() {
	return prenom;
    }

    public void setPrenom(String prenom) {
	this.prenom = prenom;
    }

    public Integer getAge() {
	return age;
    }

    public void setAge(Integer age) {
	this.age = age;
    }

    public Adresse getAdresse() {
	return adresse;
    }

    public void setAdresse(Adresse adresse) {
	this.adresse = adresse;
    }

    public String getTelephoneFixe() {
	return telephoneFixe;
    }

    public void setTelephoneFixe(String telephoneFixe) {
	this.telephoneFixe = telephoneFixe;
    }

    public String getTelephoneMobile() {
	return telephoneMobile;
    }

    public void setTelephoneMobile(String telephoneMobile) {
	this.telephoneMobile = telephoneMobile;
    }

    public List<Sport> getListeSports() {
	return listeSports;
    }

    public void setListeSports(List<Sport> listeSports) {
	this.listeSports = listeSports;
    }

    public Double getMontantMaxPari() {
	return montantMaxPari;
    }

    public void setMontantMaxPari(Double montantMaxPari) {
	this.montantMaxPari = montantMaxPari;
    }

    public Utilisateur getUtilisateur() {
	return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
	this.utilisateur = utilisateur;
    }

}
