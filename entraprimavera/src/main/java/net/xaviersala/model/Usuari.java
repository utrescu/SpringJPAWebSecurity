package net.xaviersala.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entitat bàsica que emmagatzema l'usuari.
 * 
 * Defineix dos tipus d'usuaris: 
 *  - 0: USUARI
 *  - 1: ADMIN
 *  
 * @author Xavier Sala
 *
 */
@Entity
public class Usuari {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;
  
  @NotNull 
  @NotEmpty
  private String username;
  
  @NotNull
  @NotEmpty
  private String password;
  
  int role;


  public Usuari() {
    
  }
  
  public Usuari(String username, String password) {
    this.username = username;
    this.password = password;
    role = 0;
  }
  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * @param username the username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * @return the contrasenya
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param contrasenya the contrasenya to set
   */
  public void setPassword(String contrasenya) {
    this.password = contrasenya;
  }
  
  /**
   * @return the role
   */
  public int getRole() {
    return role;
  }

  /**
   * @param role the role to set
   */
  public void setRole(int role) {
    this.role = role;
  }

  
  public String toString() {
    return username;
  }
  
  
}
