/**
 * 
 */
package net.xaviersala.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador web que captura les peticions que arriben a diferents
 * URL per fer les tasques.
 * 
 * @author Xavier Sala
 *
 */
@Controller
public class loginController {
  
  
  @RequestMapping("/")
  public String homePage() {
    return "redirect:index.html";
  }
  
  @RequestMapping("/login")
  public String formulari() {
    
    if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
      return "redirect:/";
    }
    
    return "login";
  }
  
  @RequestMapping("/logout")
  public String sortir() {
    return "logout";
  }
  
//  @RequestMapping(value="/login", method=RequestMethod.POST)
//  public String comprova(@PathVariable("usuari") Optional<String> usuari,
//                         @PathVariable("contra") Optional<String> contra) {
//    return "ok";
//  }
  
  @RequestMapping(value="/secret/*")
  public String haEntrat(Model model) {
    
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String name = auth.getName(); //get logged in username

    model.addAttribute("username", name);
    return "dins";
  }
  
  @Secured("ROLE_ADMIN")
  @RequestMapping(value="/admin/*")
  public String administrador() {
    return "admin";
  }

}
