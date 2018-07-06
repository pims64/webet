package com.webet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.webet.services.LoginService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginService loginService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

	http.authorizeRequests().antMatchers("/static/**").permitAll().anyRequest().authenticated().and().formLogin()
		.loginPage("/utilisateurcontrolleur/goToAuth").loginProcessingUrl("/login")
		.defaultSuccessUrl("/clientcontroller/goToSite", true)
		.failureUrl("/utilisateurcontrolleur/goToAuth?error=true").permitAll().and().logout()
		.invalidateHttpSession(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/utilisateurcontrolleur/goToAuth?logout=true").permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth.inMemoryAuthentication().withUser("user").password("{noop}123").roles("USER");
	auth.inMemoryAuthentication().withUser("admin").password("{noop}123").roles("ADMIN");
	auth.inMemoryAuthentication().withUser("guest").password("{noop}123").roles("GUEST");
	auth.inMemoryAuthentication().withUser("root").password("123").roles("USER", "ADMIN", "GUEST");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(loginService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
    }
}
