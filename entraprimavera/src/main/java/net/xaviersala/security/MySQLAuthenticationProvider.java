package net.xaviersala.security;

import net.xaviersala.repository.UsuarisService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class MySQLAuthenticationProvider
           extends AbstractUserDetailsAuthenticationProvider {

  private static final Log log = LogFactory.getLog(MySQLAuthenticationProvider.class);
  
  @Autowired
  UsuarisService usuarisService;
  
  @Override
  protected void additionalAuthenticationChecks(UserDetails arg0,
      UsernamePasswordAuthenticationToken arg1) throws AuthenticationException {
    // No cal fer res extra...
    
  }

  @Override
  protected UserDetails retrieveUser(String username,
      UsernamePasswordAuthenticationToken auth) throws AuthenticationException {
    
 // Obtenir el que cal per fer les comprovacions
    String password = (String) auth.getCredentials();
    if (!StringUtils.hasText(password)) {
      log.warn("Contrasenya en blanc " + username);
      throw new BadCredentialsException("No es permeten els usuaris sense contrasenya");
    }
    
    // Comprovar que l'usuari existeix... 
    
    UserDetails usuari = usuarisService.loadUserByUsername(username);
    if (usuari == null) {
      log.warn("Usuari " + username + " no trobat");
      throw new UsernameNotFoundException("Usuari no trobat");
    }
    
    if (!password.equals(usuari.getPassword())) {
      log.warn("Username "+ username +" password " + password + ": invalid password");
      throw new BadCredentialsException("Usuari o contrasenya incorrectes");
    }
        
    return usuari;
  }
  
  

  
}
