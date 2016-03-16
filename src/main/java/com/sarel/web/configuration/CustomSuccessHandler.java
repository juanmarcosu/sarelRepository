package com.sarel.web.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
    @Override
    protected void handle(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
 
        if (response.isCommitted()) {
            System.out.println("Can't redirect");
            return;
        }
 
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
    
    protected String determineTargetUrl(Authentication authentication) {
    	String url="";
    	
        Collection<? extends GrantedAuthority> authorities =  authentication.getAuthorities();
        
		List<String> roles = new ArrayList<String>();

		for (GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());
		}

		if (isAdministrador(roles)) {
			url = "/admin";
		} else if (isLaboratorista(roles)) {
			url = "/db";
		} else if (isConsultor(roles)) {
			url = "/home";
		} else {
			url="/Access_Denied";
		}

		return url;
    }
 
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
    
	private boolean isConsultor(List<String> roles) {
		if (roles.contains("ROLE_CONSULTOR")) {
			return true;
		}
		return false;
	}

	private boolean isLaboratorista(List<String> roles) {
		if (roles.contains("ROLE_LABORATORISTA")) {
			return true;
		}
		return false;
	}

	private boolean isAdministrador(List<String> roles) {
		if (roles.contains("ROLE_ADMINISTRADOR")) {
			return true;
		}
		return false;
	}

}
