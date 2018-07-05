package com.webet.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.webet.entities.Client;
import com.webet.entities.Utilisateur;

public class UtilisateurPrincipal implements UserDetails {

    private static final long serialVersionUID = -5270061614463868043L;

    private Utilisateur utilisateur;

    private Client client;

    public UtilisateurPrincipal(Client client) {
	this.utilisateur = client.getUtilisateur();
	this.client = client;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	Collection<GrantedAuthority> authorities = new ArrayList<>();
	authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
	return authorities;
    }

    public Client getClient() {
	return client;
    }

    @Override
    public String getPassword() {
	return utilisateur.getMotDePasse();
    }

    @Override
    public String getUsername() {
	return utilisateur.getEmail();
    }

    public Utilisateur getUtilisateur() {
	return utilisateur;
    }

    @Override
    public boolean isAccountNonExpired() {
	return true;
    }

    @Override
    public boolean isAccountNonLocked() {
	return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
	return true;
    }

    @Override
    public boolean isEnabled() {
	return true;
    }
}
