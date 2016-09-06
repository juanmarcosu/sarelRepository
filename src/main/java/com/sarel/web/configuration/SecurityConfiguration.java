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
	  	.antMatchers("/agregarEXPEDIENTE_LABORATORIO").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	.antMatchers("/editarEXPEDIENTE_LABORATORIO").access("hasRole('ADMINISTRADOR')")
	  	.antMatchers("/eliminarEXPEDIENTE_LABORATORIO").access("hasRole('ADMINISTRADOR')")
	  	
	  	.antMatchers("/consultarPERFIL_LIPIDICO").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA') or hasRole('CONSULTOR')")
	  	.antMatchers("/agregarPERFIL_LIPIDICO").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	.antMatchers("/editarPERFIL_LIPIDICO").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	.antMatchers("/eliminarPERFIL_LIPIDICO").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	.antMatchers("/imprimirPERFIL_LIPIDICO").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	
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
	  	
	  	.antMatchers("/consultarCOLESTEROL_TRIGLICERIDOS").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA') or hasRole('CONSULTOR')")
	  	.antMatchers("/agregarCOLESTEROL_TRIGLICERIDOS").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	.antMatchers("/editarCOLESTEROL_TRIGLICERIDOS").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	.antMatchers("/eliminarCOLESTEROL_TRIGLICERIDOS").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	
	  	.antMatchers("/consultarGLUCOSA_PRE_Y_POST").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA') or hasRole('CONSULTOR')")
	  	.antMatchers("/agregarGLUCOSA_PRE_Y_POST").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	.antMatchers("/editarGLUCOSA_PRE_Y_POST").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	.antMatchers("/eliminarGLUCOSA_PRE_Y_POST").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	
	  	.antMatchers("/consultarPRUEBA_SEROLOGICA").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA') or hasRole('CONSULTOR')")
	  	.antMatchers("/agregarPRUEBA_SEROLOGICA").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	.antMatchers("/editarPRUEBA_SEROLOGICA").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	.antMatchers("/eliminarPRUEBA_SEROLOGICA").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	
	  	.antMatchers("/consultarPRUEBAS_HEMATOLOGICAS").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA') or hasRole('CONSULTOR')")
	  	.antMatchers("/agregarPRUEBAS_HEMATOLOGICAS").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	.antMatchers("/editarPRUEBAS_HEMATOLOGICAS").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	.antMatchers("/eliminarPRUEBAS_HEMATOLOGICAS").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	
	  	.antMatchers("/consultarHEMATOLOGIA_COMPLETA").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA') or hasRole('CONSULTOR')")
	  	.antMatchers("/agregarHEMATOLOGIA_COMPLETA").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	.antMatchers("/editarHEMATOLOGIA_COMPLETA").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	.antMatchers("/eliminarHEMATOLOGIA_COMPLETA").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	
	  	.antMatchers("/consultarEXAMEN_ORINA").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA') or hasRole('CONSULTOR')")
	  	.antMatchers("/agregarEXAMEN_ORINA").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	.antMatchers("/editarEXAMEN_ORINA").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	.antMatchers("/eliminarEXAMEN_ORINA").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	
	  	.antMatchers("/consultarEXAMEN_HECES").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA') or hasRole('CONSULTOR')")
	  	.antMatchers("/agregarEXAMEN_HECES").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	.antMatchers("/editarEXAMEN_HECES").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	.antMatchers("/eliminarEXAMEN_HECES").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	
	  	.antMatchers("/buscarPruebaVIH").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA') or hasRole('CONSULTOR')")
	  	.antMatchers("/consultarPRUEBA_VIH").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA') or hasRole('CONSULTOR')")
	  	.antMatchers("/agregarPRUEBA_VIH").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	.antMatchers("/editarPRUEBA_VIH").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	.antMatchers("/eliminarPRUEBA_VIH").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')")
	  	
	  	.antMatchers("/verExpedienteLaboratorio").access("hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA') or hasRole('CONSULTOR')")
	  	//.and().formLogin().loginPage("/login")
	  	.and().formLogin().loginPage("/login").successHandler(customSuccessHandler)
	  	.usernameParameter("ssoId").passwordParameter("password")
	  	.and().csrf()
	  	.and().exceptionHandling().accessDeniedPage("/Access_Denied");
	}
}
