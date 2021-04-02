package com.vzla.jc.web;

import com.vzla.jc.domain.Persona;
import com.vzla.jc.servicio.PersonaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import org.springframework.validation.Errors;

@Controller
@Slf4j
public class ControladorInicio {

    @Autowired
    private PersonaService personaService;
    
    @GetMapping("/")
    
    public String inicio(Model model) {
        
        var personas = personaService.listarPersonas();
        log.info("Ejecutando el controlador Spring MVC");

        model.addAttribute("personas", personas);
        return "index";
    }
    
    @GetMapping("/agregar")
    public String agregar(Persona persona){
        return "modificar";
    }

    //Se mapeo la ruta al formulario con la accion Guardar
    @PostMapping("/guardar")
    //Se crea el metodo guardar con el argumento de tipo persona
    //Se invoca la interface Persona.service para que guarde los registros
    //Se retorna a la pagina de inicio con "redirect:/"
    public String guardar (@Valid Persona persona, Errors errores){
        if(errores.hasErrors()){
            return "modificar";
        }
        personaService.guardar(persona);
        return "redirect:/";
    }
    
    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model){
        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        return "modificar";
    }
    
    @GetMapping("/eliminar")
    public String eliminar (Persona persona){
        personaService.eliminar(persona);
        return "redirect:/";
    }
}
