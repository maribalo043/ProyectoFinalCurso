package com.mario.proyect.utilidades;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class GeneralController {

    @GetMapping("/torneo")
    public ModelAndView getTorneo() {
    	ModelAndView model = new ModelAndView();
    	model.setViewName("generalHTML/Torneo");
        return model;
    }
    
    @GetMapping("/nuestrosEquipos")
    public ModelAndView getEquipos() {
    	ModelAndView model = new ModelAndView();
    	model.setViewName("generalHTML/NuestrosEquipos");
        return model;
    }
    
    @GetMapping("/sobreNosotros")
    public ModelAndView getSobreNosotros() {
    	ModelAndView model = new ModelAndView();
    	model.setViewName("generalHTML/SobreNosotros");
        return model;
    }
    
    @GetMapping("/contacto")
    public ModelAndView getContacto() {
    	ModelAndView model = new ModelAndView();
    	model.setViewName("generalHTML/Contacto");
        return model;
    }
    
    @GetMapping("/links")
    public ModelAndView getLinks() {
    	ModelAndView model = new ModelAndView();
    	model.setViewName("generalHTML/Links");
        return model;
    }
    
    @GetMapping("/avisoLegal")
    public ModelAndView getAvisoLegal() {
    	ModelAndView model = new ModelAndView();
    	model.setViewName("generalHTML/AvisoLegal");
        return model;
    }
    
    @GetMapping("/politicaPrivacidad")
    public ModelAndView getPoliticaDePrivacidad() {
    	ModelAndView model = new ModelAndView();
    	model.setViewName("generalHTML/PoliticaPrivacidad");
        return model;
    }

    @GetMapping("/login")
    public ModelAndView getLogin() {
    	ModelAndView model = new ModelAndView();
    	model.setViewName("index");
        return model;
    }
    
    @GetMapping("/logout")
    public ModelAndView getLogOut() {
    	ModelAndView model = new ModelAndView();
    	model.setViewName("index");
        return model;
    }
    
}
