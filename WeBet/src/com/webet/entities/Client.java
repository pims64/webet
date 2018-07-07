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
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "{error.client.nom.obligatoire}")
    private String nom;

    @NotEmpty(message = "{error.client.prenom.obligatoire}")
    private String prenom;

    @NotNull(message = "{error.client.age.obligatoire}")
    @Min(value = 18, message = "{error.client.age.superieur18}")
    private Integer age;

    @Valid
    @Embedded
    private Adresse adresse;

    private String telephoneFixe;

    private String telephoneMobile;

    @OneToMany
    private List<Sport> listeSports;

    @NotNull(message = "{error.client.montantMax.obligatoire}")
    private Double montantMaxPari = 500.0;

    @Valid
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
