package net.xaviersala.repository;

import net.xaviersala.model.Usuari;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositori per recuperar les dades 'Usuari' de la 
 * base de dades MySQL.
 * 
 * Només em cal cercar per username
 * 
 * @author Xavier Sala
 *
 */
@Repository
public interface UsuariRepository extends CrudRepository<Usuari, Long> {
  
  Usuari findByUsername(String username);
  

}
