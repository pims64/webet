package com.webet.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{error.utilisateur.role.obligatoire}")
    @Enumerated(EnumType.STRING)
    private ERole role;

    @NotEmpty(message = "{error.utilisateur.email.obligatoire}")
    @Email(message = "{error.utilisateur.email.malforme}")
    @Column(unique = true, nullable = false)
    private String email;

    @NotEmpty(message = "{error.utilisateur.motDePasse.obligatoire}")
    private String motDePasse;

    public Utilisateur() {
    }

    @Override
    public String toString() {
	return "Utilisateur [id=" + id + ", role=" + role + ", email=" + email + ", motDePasse=" + motDePasse + "]";
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getMotDePasse() {
	return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
	this.motDePasse = motDePasse;
    }

    public ERole getRole() {
	return role;
    }

    public void setRole(ERole role) {
	this.role = role;
    }

}
