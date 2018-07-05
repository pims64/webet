package com.webet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.webet.config.UtilisateurPrincipal;
import com.webet.dao.IUtilisateurJpaRepository;
import com.webet.entities.Utilisateur;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private IUtilisateurJpaRepository utilisateurRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	Utilisateur utilisateur = utilisateurRepo.findByEmail(username);
	if (null == utilisateur) {
	    throw new UsernameNotFoundException("No user found with username: " + username);
	}
	return new UtilisateurPrincipal(utilisateur);
    }
}
