package net.xaviersala.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * Configuració de Spring Security però en comptes de fer-ho
 * a l'estil antic (via XML) amb una classe de tipus @Configuration
 * 
 * La idea és definir COM es fa per autenticar els usuaris de la 
 * web i també decidir on poden entrar i on no els usuaris no identificats.
 * 
 * @author xavier
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
  @Autowired
  private MySQLAuthenticationProvider mySQLAuthenticationProvider;
      
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
      
      // Defineix usuaris en memòria
      auth.inMemoryAuthentication()
          .withUser("admin").password("admin").roles("ADMIN");
      // Usuaris en MySQL
      auth.authenticationProvider(mySQLAuthenticationProvider);  
    }

    /**
     * Seguretat web no necessària per les adreces de recursos bàsics: 
     * CSS, Javascript, Imatges, tipus de lletres.
     */
    @Override
      public void configure(WebSecurity web) throws Exception {
          web
              .ignoring()
                  .antMatchers("/js/**","/css/**","/img/**","/fonts/**");
      }
    
    /**
     * Defineix per quines adreces es podrà accedir sense identificació i per 
     * quines caldrà identificació. 
     * Com es fa per identificar-los (formlogin) o desconnectar-los (/logout)
     * També es pot definir quin tipus d'usuari cal per cada una 
     * de les URL
     */
    protected void configure(HttpSecurity http) throws Exception {
      http
          .authorizeRequests()
            .antMatchers("/*").permitAll()
              .antMatchers("/resources/**").permitAll() 
              .antMatchers("/secret/**").authenticated()
              .antMatchers("/admin/**").hasRole("ADMIN")
              .anyRequest().authenticated()
              .and()
          .formLogin()
              .loginPage("/login")                  
              .permitAll()
              .and()
          .logout()
            .logoutUrl("/logout") 
            .logoutSuccessUrl("/")
              .permitAll();
    }
    
    

}
