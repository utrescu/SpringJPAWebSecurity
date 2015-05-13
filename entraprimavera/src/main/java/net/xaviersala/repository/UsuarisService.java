package net.xaviersala.repository;

import net.xaviersala.model.Usuari;
import net.xaviersala.security.UsuariDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Servei de localització d'Usuaris personalitzats. 
 * 
 * Implementa 'UserDetailsService' que és el que fa servir
 * Spring per autenticar.
 * 
 * S'ha d'implementar loadUserByUsername() que ha de retornar
 * un objecte UserDetails
 * 
 * @author xavier
 *
 */
@Service
public class UsuarisService  implements UserDetailsService {

  
  UsuariRepository usuariRepository;
  
  @Autowired
  public UsuarisService(UsuariRepository usuariRepository) {
    this.usuariRepository = usuariRepository;
  }
  
  @Override
  public UserDetails loadUserByUsername(String nom)
      throws UsernameNotFoundException {
      Usuari usuari = usuariRepository.findByUsername(nom);
      
      if (usuari == null) {
        // return null;
        throw new BadCredentialsException("No es permeten els usuaris sense nom");
      }
            
      return new UsuariDetails(usuari);      
  }


  
  
}
