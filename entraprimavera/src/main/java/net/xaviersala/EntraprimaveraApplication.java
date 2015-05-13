package net.xaviersala;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Es tracta de fer un programa que permeti autenticar un usuari
 * a través d'un formulari web.
 * 
 * - Els usuaris han d'estar definits en una taula de MySQL i es farà
 * a través de JPA
 * - Es definiran dos tipus d'usuaris els USER i els ADMIN amb accessos
 * especialitzats.
 * 
 * Fa servir Spring Security per protegir les URL: 
 * 
 *   - /secret/ : només permet accés als usuaris que tinguin ROLE USER 
 *                o identificats
 *   - /admin/  : Només accessible pels usuaris amb ROLE admin (admin/admin)
 * 
 * Es delega tota la gestió dels usuaris a Spring Security
 * 
 * @author Xavier Sala
 *
 */
@SpringBootApplication
public class EntraprimaveraApplication {

    public static void main(String[] args) {
        SpringApplication.run(EntraprimaveraApplication.class, args);
    }
    
 

}
