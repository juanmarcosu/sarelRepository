package com.sarel.web.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.context.annotation.Bean;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;
	
	@Autowired
	CustomSuccessHandler customSuccessHandler;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	  http.authorizeRequests()
	  	.antMatchers("/", "/home").permitAll()
	  	//.antMatchers("/", "/home").access("hasRole('USER')")
	  	.antMatchers("/admin/**","/newuser").access("hasRole('ADMINISTRADOR')")
	  	.antMatchers("/db/**").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	.antMatchers("/buscarPaciente").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA') or hasRole('CONSULTOR')")
	  	
	  	.antMatchers("/consultarPERFIL_LIPIDICO").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA') or hasRole('CONSULTOR')")
	  	.antMatchers("/agregarPERFIL_LIPIDICO").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	.antMatchers("/editarPERFIL_LIPIDICO").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	.antMatchers("/eliminarPERFIL_LIPIDICO").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	
	  	.antMatchers("/consultarPRUEBA_EMBARAZO").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA') or hasRole('CONSULTOR')")
	  	.antMatchers("/agregarPRUEBA_EMBARAZO").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	.antMatchers("/editarPRUEBA_EMBARAZO").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	.antMatchers("/eliminarPRUEBA_EMBARAZO").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	
	  	.antMatchers("/consultarACIDO_URICO").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA') or hasRole('CONSULTOR')")
	  	.antMatchers("/agregarACIDO_URICO").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	.antMatchers("/editarACIDO_URICO").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	.antMatchers("/eliminarACIDO_URICO").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	
	  	.antMatchers("/consultarPRUEBA_VDRL").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA') or hasRole('CONSULTOR')")
	  	.antMatchers("/agregarPRUEBA_VDRL").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	.antMatchers("/editarPRUEBA_VDRL").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	.antMatchers("/eliminarPRUEBA_VDRL").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	
	  	.antMatchers("/verExpedienteLaboratorio").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA') or hasRole('CONSULTOR')")
	  	//.and().formLogin().loginPage("/login")
	  	.and().formLogin().loginPage("/login").successHandler(customSuccessHandler)
	  	.usernameParameter("ssoId").passwordParameter("password")
	  	.and().csrf()
	  	.and().exceptionHandling().accessDeniedPage("/Access_Denied");
	}
}
