package com.webet.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.webet.entities.Utilisateur;

public class UtilisateurPrincipal implements UserDetails {

    private static final long serialVersionUID = -5270061614463868043L;

    private Utilisateur utilisateur;

    public UtilisateurPrincipal(Utilisateur utilisateur) {
	this.utilisateur = utilisateur;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	Collection<GrantedAuthority> authorities = new ArrayList<>();
	authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
	return authorities;
    }

    @Override
    public String getPassword() {
	return utilisateur.getMotDePasse();
    }

    @Override
    public String getUsername() {
	return utilisateur.getEmail();
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
