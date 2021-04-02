
package com.vzla.jc.dao;

import com.vzla.jc.domain.Persona;
import org.springframework.data.repository.CrudRepository;


public interface PersonaDao extends CrudRepository<Persona, Long>{
    
}
