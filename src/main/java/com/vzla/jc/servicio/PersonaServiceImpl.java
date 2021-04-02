package com.vzla.jc.servicio;

import com.vzla.jc.dao.PersonaDao;
import com.vzla.jc.domain.Persona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaDao personaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Persona> listarPersonas() {
        return (List<Persona>) personaDao.findAll();
        /*Se invoca al objeto personaDao junto con el metodo findAll
        y este retorna una Lista con todos los datos consultados*/
    }

    @Override
    @Transactional
    public void guardar(Persona persona) {
        personaDao.save(persona);
        /*Se invoca al objeto personaDao junto con el metodo Save y 
       se le pasa el objeto persona para guardar los datos*/

    }

    @Override
    @Transactional
    public void eliminar(Persona persona) {
        personaDao.delete(persona);
//Se invoca al objeto personaDao junto con el metodo Delete y se le pasa el objeto persona
    }

    @Override
    @Transactional(readOnly = true)
    public Persona encontrarPersona(Persona persona) {
        return personaDao.findById(persona.getIdPersona()).orElse(null);
        /*Se invoca al objeto personaDao junto con el metodo findById con
el parametro objeto persona al encontrar el ID buscado lo retorna y en tal 
        caso de no encontrar el ID retorna un NULL
         */
    }

}
