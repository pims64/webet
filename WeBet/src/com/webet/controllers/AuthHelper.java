package com.webet.controllers;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.webet.config.UtilisateurPrincipal;
import com.webet.entities.ERole;
import com.webet.entities.Utilisateur;

/**
 * Une classe qui facilite l'accès aux informations d'authentification. Aide à
 * récupérer l'utilisateur connecté et son rôle.
 */
public class AuthHelper {

    /**
     * Retourne le rôle de l'utilisateur connecté.
     *
     * @return le role de l'utilisateur connecté
     */
    public static ERole getRole() {
	return getUtilisateur().getRole();
    }

    /**
     * Retourne l'utilisateur connecté.
     *
     * @return l'utilisateur connecté
     */
    public static Utilisateur getUtilisateur() {
	return getPrincipal().getUtilisateur();
    }

    public static UtilisateurPrincipal getPrincipal() {
	return (UtilisateurPrincipal) getAuthentication().getPrincipal();
    }

    public static Collection<? extends GrantedAuthority> getAuthorities() {
	return getAuthentication().getAuthorities();
    }

    public static Authentication getAuthentication() {
	return SecurityContextHolder.getContext().getAuthentication();
    }
}
