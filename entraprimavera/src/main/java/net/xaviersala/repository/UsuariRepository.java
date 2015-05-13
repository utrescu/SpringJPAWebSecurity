package net.xaviersala.repository;

import net.xaviersala.model.Usuari;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariRepository extends CrudRepository<Usuari, Long> {
  
  Usuari findByUsername(String username);
  

}
