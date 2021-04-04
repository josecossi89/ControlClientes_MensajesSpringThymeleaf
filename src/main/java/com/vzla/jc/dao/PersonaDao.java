
package com.vzla.jc.dao;

import com.vzla.jc.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PersonaDao extends JpaRepository<Persona, Long>{
    
}
