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
 * @author Xavier Sala
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
  @Autowired
  private MySQLAuthenticationProvider mySQLAuthenticationProvider;
      
    /**
     * La idea d'aquí es definir com o què fara l'autenticació. 
     * 
     * En aquest cas defineixo dos autenticadors: 
     * 
     * - Autenticador en memòria: ve definit per Spring
     * - Autenticador MySQL: Aquest l'he definit jo en una altra classe. Se li
     *                       ha de dir com es farà per comprovar els usuaris 
     * 
     * @param auth
     * @throws Exception
     */
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
     * 
     * Per tant totes aquestes adreces es defineixen amb ignoring()
     * que en els fitxers XML és 'security=none'
     * 
     */
    @Override
      public void configure(WebSecurity web) throws Exception {
          web
              .ignoring()
                  .antMatchers("/js/**","/css/**","/img/**","/fonts/**");
      }
    
    /**
     * Defineix per quines adreces es podrà accedir sense identificació i 
     * per quines caldrà identificació. L'ORDRE ÉS IMPORTANT!
     * 
     *    authorizeRequests().antMatchers(URL)
     *    ... permitAll(): Permet connexions a tothom
     *    ... authenticated(): Requereix autenticació
     *    ... hasRole("A") : Defineix el ROLE de l'usuari necessari
     *    anyRequest(): Són totes les altres
     * 
     * Després es defineix com es fa per identificar-los (formlogin) 
     *    ...loginPage("/login"): Pàgina amb el formulari. els camps 
     *    d'usuari i contrasenya han de ser 'username' i 'password'
     * o desconnectar-los (/logout)
     *   - l'accés a logout s'ha de fer amb POST i se li pot dir on
     *   anar en quan desconnecti.
     *    
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
