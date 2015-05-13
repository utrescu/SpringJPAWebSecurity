package net.xaviersala.security;

import java.util.ArrayList;
import java.util.List;

import net.xaviersala.model.Usuari;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuariDetails implements UserDetails {
  
  /**
   * 
   */
  private static final long serialVersionUID = 6301602624194908776L;
  private Usuari usuari;
  
  public UsuariDetails(Usuari usuari) {
    this.usuari = usuari;
  }

  @Override
  public String getPassword() {    
    return usuari.getPassword();
  }

  @Override
  public String getUsername() {    
    return usuari.getUsername();
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
  
  /**
   * Convertir el ROLE de l'usuari en un ROLE d'Spring Security.
   * 
   * @param role Tipus d'usuari en forma d'string
   * @return Llista amb els roles
   */
  public List<GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
    
    switch (usuari.getRole()) {
    case 0:
      authList.add(new SimpleGrantedAuthority("ROLE_USER"));
      break;
    case 1:
      authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }    
    return authList;
 }

}
